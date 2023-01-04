package `in`.junkielabs.adsmeta.domain.base.result

/**
 * Created by Niraj on 21-12-2022.
 */
inline fun <T> DomainResult<T>.mapSuccess(
    crossinline onResult: DomainResult.Success<T>.() -> DomainResult<T>,
): DomainResult<T> {
    if (this is DomainResult.Success) {
        return onResult(this)
    }
    return this
}