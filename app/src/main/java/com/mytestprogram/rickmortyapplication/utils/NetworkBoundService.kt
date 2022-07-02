package com.mytestprogram.rickmortyapplication.utils

import kotlinx.coroutines.flow.*

//query - get data from db, fetch - get data from API, saveFetchResults - save data from api to db
//shouldFetch - boolean param that says should fetch data from api to db or not (default - true(update items in db))
//flow is coldFlow
fun <ResultType, RequestType> networkBoundService(
    query: () -> Flow<ResultType>,
    fetch: suspend () -> RequestType,
    saveFetchResults: suspend (RequestType) -> Unit,
    shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResults(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}