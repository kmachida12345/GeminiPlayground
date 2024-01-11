package com.github.kmachida12345.geminiplayground

/**
 * A sealed hierarchy describing the state of the text generation.
 */
sealed interface SummarizeUiState {

    /**
     * Empty state when the screen is first shown
     */
    object Initial : SummarizeUiState

    /**
     * Still loading
     */
    object Loading : SummarizeUiState

    /**
     * Text has been generated
     */
    data class Success(
        val outputText: String
    ) : SummarizeUiState

    /**
     * There was an error generating text
     */
    data class Error(
        val errorMessage: String
    ) : SummarizeUiState
}