package com.virrub.orgs.imageForm

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.virrub.orgs.databinding.ImageFormBinding
import com.virrub.orgs.extensions.tryLoad

class ImageFormDialog(
    private var context: Context,
    private var url: String?
) {
    private val binding by lazy {
        ImageFormBinding.inflate(LayoutInflater.from(context)).apply {
            if (url == null) {
                formImageInput.setText("https://asia.olympus-imaging.com/content/000107506.jpg")
            }
            url?.let {
                formImageInput.setText(it)
                imageFormImage.tryLoad(it)
            }
            imageFormButton.setOnClickListener {
                loadImageFromURLToDialog()
            }
        }
    }

    private val imageURL: String
        get() {
            return binding.formImageInput.text.toString()
        }

    private fun loadImageFromURLToDialog() {
        binding.imageFormImage.tryLoad(imageURL)
    }

    fun showImageSelector(
        onSuccess: (String) -> Unit = {},
        onCancel: () -> Unit = {}
    ) {
        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("confirmar") { _, _ ->
                onSuccess(imageURL)
            }
            .setNegativeButton("cancelar") { _, _ ->
                onCancel()
            }
            .show()
    }
}