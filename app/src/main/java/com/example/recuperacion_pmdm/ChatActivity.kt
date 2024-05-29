package com.example.recuperacion_pmdm

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var editTextMessage: EditText
    private lateinit var buttonSend: Button

    private val messages = mutableListOf<Message>()
    private val chatAdapter = ChatAdapter(messages)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        recyclerView = findViewById(R.id.recyclerView)
        editTextMessage = findViewById(R.id.editTextMessage)
        buttonSend = findViewById(R.id.buttonSend)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = chatAdapter

        buttonSend.setOnClickListener {
            val messageText = editTextMessage.text.toString()
            buttonSend.setOnClickListener {

                //TODO : Set up usernameText to the logged in username

                val usernameText = "Usuario"
                val messageText = editTextMessage.text.toString()
                if (messageText.isNotBlank()) {
                    val message = Message(user = usernameText, text = messageText) // Replace "User" with dynamic username if available
                    messages.add(message)
                    chatAdapter.notifyItemInserted(messages.size - 1)
                    recyclerView.scrollToPosition(messages.size - 1)
                    editTextMessage.text.clear()
                }
            }
        }
    }
}