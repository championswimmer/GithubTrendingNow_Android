package tech.arnav.githubtrending.data

/**
 * A wrapper over objects received from Api responses, to additionally
 * indicate the status of the API call (loading/success/failure)
 */
class ApiResponse<T> {
    enum class Status {
        NONE, LOADING, SUCCESS, FAILURE
    }

    private var data: T? = null
    private var errorMessage: String? = null

    var status: Status = Status.NONE

    fun set(status: Status, data: T?, errorMessage: String? = null) {
        this.status = status
        this.data = data
        this.errorMessage = errorMessage
    }


    fun set(data: T?) {
        this.data = data
        this.status = if (data != null) {
            errorMessage = null // clear previous error message if any
            Status.SUCCESS
        } else {
            Status.FAILURE
        }
    }

    fun get() = data

    fun getErrorMessage() = errorMessage

}