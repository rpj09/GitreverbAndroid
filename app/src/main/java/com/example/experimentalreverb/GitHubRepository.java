package com.example.experimentalreverb;


public class GitHubRepository {
    private int id;                 // The repository's ID
    private String name;            // The repository's name
    private String description;     // The repository's description
    private String language;        // The repository's main programming language
    private int stargazers_count;   // The number of stars the repository has received
    private int forks_count;        // The number of forks the repository has received
    private int watchers_count;     // The number of users watching the repository
    private String created_at;      // The date and time the repository was created
    private String updated_at;      // The date and time the repository was last updated
    private String html_url;        // The URL of the repository's GitHub page

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public int getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(int watchers_count) {
        this.watchers_count = watchers_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    @Override
    public String toString() {
        return "GitHubRepository{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", stargazers_count=" + stargazers_count +
                ", forks_count=" + forks_count +
                ", watchers_count=" + watchers_count +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", html_url='" + html_url + '\'' +
                '}';
    }
}
