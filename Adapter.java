package com.example.chatbot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyView> {

    List<Message> messageList;
    public Adapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,null);
        MyView myView = new MyView(chatView);
        return myView;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        Message message = messageList.get(position);
        if(message.getSentBy().equals(Message.sent_by_user)) {
            holder.leftChat.setVisibility(View.GONE); //hiding the left chat layout when the message comes from me
            holder.rightChat.setVisibility(View.VISIBLE); //right side is visible
            holder.rightText.setText(message.getMessage());
        }
        else{
            holder.rightChat.setVisibility(View.GONE); //hiding the right chat layout when the message comes from me
            holder.leftChat.setVisibility(View.VISIBLE); //left side is visible
            holder.leftText.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MyView extends RecyclerView.ViewHolder{
        LinearLayout leftChat,rightChat;
        TextView leftText, rightText;
        public MyView(@NonNull View itemView) {
            super(itemView);
            leftChat = itemView.findViewById(R.id.left_chat);
            rightChat = itemView.findViewById(R.id.right_chat);
            leftText = itemView.findViewById(R.id.left_text);
            rightText = itemView.findViewById(R.id.right_text);
        }
    }
}



