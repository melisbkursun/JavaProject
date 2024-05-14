package com.example.chatbot;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView welcomeTextView;
    EditText messageEditText;
    ImageButton sendButton;
    List<Message> messageList;
    Adapter messageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        messageList= new ArrayList<>();
        recyclerView=findViewById(R.id.recycler_view);
        welcomeTextView=findViewById(R.id.welcome_text);
        messageEditText=findViewById(R.id.message_edit_text);
        sendButton=findViewById(R.id.send_button);

        //setting up the recycler view
        messageAdapter = new Adapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager m = new LinearLayoutManager(this);
        m.setStackFromEnd(true);       //to scroll from below to up
        recyclerView.setLayoutManager(m);

        sendButton.setOnClickListener((v)->{
            String question = messageEditText.getText().toString().trim();
            addToChat(question,Message.sent_by_user);
            messageEditText.setText(""); //clear the "write here" after sent
            welcomeTextView.setText(""); //clear the Welcome Text after sent
        });
    }

    void addToChat(String message, String sentBy){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new Message(message,sentBy)); //after this we need to notify the adapter
                messageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount()); //this will scroll to the last position

            }
        });
    }
}