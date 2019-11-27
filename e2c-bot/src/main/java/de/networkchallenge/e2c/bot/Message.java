package de.networkchallenge.e2c.bot;

public class Message {

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getForward_from() {
        return forward_from;
    }

    public void setForward_from(User forward_from) {
        this.forward_from = forward_from;
    }

    public Chat getForward_from_chat() {
        return forward_from_chat;
    }

    public void setForward_from_chat(Chat forward_from_chat) {
        this.forward_from_chat = forward_from_chat;
    }

    public Integer getForward_from_message_id() {
        return forward_from_message_id;
    }

    public void setForward_from_message_id(Integer forward_from_message_id) {
        this.forward_from_message_id = forward_from_message_id;
    }

    public String getForward_signature() {
        return forward_signature;
    }

    public void setForward_signature(String forward_signature) {
        this.forward_signature = forward_signature;
    }

    public String getForward_sender_name() {
        return forward_sender_name;
    }

    public void setForward_sender_name(String forward_sender_name) {
        this.forward_sender_name = forward_sender_name;
    }

    public Integer getForward_date() {
        return forward_date;
    }

    public void setForward_date(Integer forward_date) {
        this.forward_date = forward_date;
    }

    public Message getReply_to_message() {
        return reply_to_message;
    }

    public void setReply_to_message(Message reply_to_message) {
        this.reply_to_message = reply_to_message;
    }

    public Integer getEdit_date() {
        return edit_date;
    }

    public void setEdit_date(Integer edit_date) {
        this.edit_date = edit_date;
    }

    public String getMedia_group_id() {
        return media_group_id;
    }

    public void setMedia_group_id(String media_group_id) {
        this.media_group_id = media_group_id;
    }

    public String getAuthor_signature() {
        return author_signature;
    }

    public void setAuthor_signature(String author_signature) {
        this.author_signature = author_signature;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private Integer message_id;
        private User from;
        private Integer date;
        private Chat chat;
        private User forward_from;
        private Chat forward_from_chat;
        private Integer forward_from_message_id;
        private String forward_signature;
        private String forward_sender_name;
        private Integer forward_date;
        private Message reply_to_message;
        private Integer edit_date;
        private String media_group_id;
        private String author_signature;
        private String text;

        public Message() {}
}
