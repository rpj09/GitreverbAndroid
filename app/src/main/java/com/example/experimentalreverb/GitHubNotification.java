package com.example.experimentalreverb;

public class GitHubNotification {
    private String id;              // The ID of the notification
    private boolean unread;         // Whether or not the notification has been read
    private String reason;          // The reason for the notification
    private String updated_at;      // The date and time the notification was last updated
    private String title;           // The title of the notification
    private String url;             // The URL of the notification
    private String latest_comment_url; // The URL of the latest comment on the notification
    private String type;            // The type of the notification (issue, pull request, etc.)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLatest_comment_url() {
        return latest_comment_url;
    }

    public void setLatest_comment_url(String latest_comment_url) {
        this.latest_comment_url = latest_comment_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GitHubNotification{" +
                "id='" + id + '\'' +
                ", unread=" + unread +
                ", reason='" + reason + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", latest_comment_url='" + latest_comment_url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
