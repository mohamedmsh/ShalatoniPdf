package com.example.shalatoni_storegepdf

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.shalatoni_storegepdf.databinding.ActivityMainBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.*

class MainActivity : AppCompatActivity() {
    val reqCode = 100
    private lateinit var pdf: Uri
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        textView =findViewById(R.id.txt)
        var storageRef = Firebase.storage.reference
        binding.btnChoose.setOnClickListener {
            val intent = Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent,"Select PDF Files..."),reqCode)


        }
        binding.btnUpload.setOnClickListener {
            binding.btnUpload.isVisible =false
            binding.btnChoose.isVisible =false
            binding.progressBar.isVisible =true
            storageRef.child("File/"+ UUID.randomUUID().toString())
                .putFile(pdf)
                .addOnSuccessListener {
                    binding.progressBar.isVisible =false
                    binding.btnUpload.isVisible =true
                    binding.btnChoose.isVisible =true
                    textView.text = "Success"
                }
                .addOnFailureListener {
                    textView.text = "Failure"
                    binding.progressBar.isVisible =false
                    binding.btnUpload.isVisible =true
                    binding.btnChoose.isVisible =true
                }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        pdf = data!!.data!!
        textView.text =pdf.toString()
    }
}