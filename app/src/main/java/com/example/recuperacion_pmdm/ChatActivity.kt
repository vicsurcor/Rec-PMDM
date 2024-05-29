package com.example.recuperacion_pmdm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var editTextMessage: EditText
    private lateinit var locationText: TextView
    private lateinit var buttonSend: Button

    private val messages = mutableListOf<ChatMessage>()
    private val chatAdapter = ChatAdapter(messages)
    private val apiService = RetrofitClient.instance.create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        val username = intent.getStringExtra("username") ?: "Unknown User"
        val location = intent.getStringExtra("location") ?: "Unknown Location"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        recyclerView = findViewById(R.id.recyclerView)
        editTextMessage = findViewById(R.id.editTextMessage)
        locationText = findViewById(R.id.locationName)
        buttonSend = findViewById(R.id.buttonSend)
        locationText.text = location
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = chatAdapter

        // Load existing messages
        loadMessages()

        buttonSend.setOnClickListener {

            val messageText = editTextMessage.text.toString()

            if (messageText.isNotBlank()) {
                val message = ChatMessage(username = username, message = messageText)
                sendMessage(message)
                editTextMessage.text.clear()
            }
        }
    }

    private fun loadMessages() {
        apiService.getMessages().enqueue(object : Callback<List<ChatMessage>> {
            override fun onResponse(call: Call<List<ChatMessage>>, response: Response<List<ChatMessage>>) {
                if (response.isSuccessful) {
                    response.body()?.let { messagesList ->
                        messages.addAll(messagesList)
                        chatAdapter.notifyDataSetChanged()
                        recyclerView.scrollToPosition(messages.size - 1)
                    }
                } else {
                    Toast.makeText(this@ChatActivity, "Failed to retrieve messages", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ChatMessage>>, t: Throwable) {
                Toast.makeText(this@ChatActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun sendMessage(message: ChatMessage) {
        apiService.sendMessage(message).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    messages.add(message)
                    chatAdapter.notifyItemInserted(messages.size - 1)
                    recyclerView.scrollToPosition(messages.size - 1)
                } else {
                    Toast.makeText(this@ChatActivity, "Message failed to send", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@ChatActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
