package com.github.kmachida12345.geminiplayground.ui.multimodal

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MultiModalViewModel(
    private val generativeModel: GenerativeModel
): ViewModel() {
    private val _uiState: MutableStateFlow<MultiModalUiState> =
        MutableStateFlow(MultiModalUiState.Initial)
    val uiState: StateFlow<MultiModalUiState> =
        _uiState.asStateFlow()

    fun reason(
        userInput: String,
        selectedImages: List<Bitmap>
    ) {
        _uiState.value = MultiModalUiState.Loading
        val prompt = "次のコメントに対し、「にゃ」を語尾につけて励すような文章を生成してください: $userInput \n例:「お疲れ様ですにゃ！」「すごいにゃ！」「頑張ったにゃ！」\nこのとき、写真の内容に言及してください。"

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val inputContent = content {
                    for (bitmap in selectedImages) {
                        image(bitmap)
                    }
                    text(prompt)
                }

                var outputContent = ""

                generativeModel.generateContentStream(inputContent)
                    .collect { response ->
                        outputContent += response.text
                        _uiState.value = MultiModalUiState.Success(outputContent)
                    }
            } catch (e: Exception) {
                _uiState.value = MultiModalUiState.Error(e.localizedMessage ?: "")
            }
        }
    }
}