package com.example.experimentalreverb;

import com.google.gson.annotations.SerializedName;

public class GitHubRepository {

    private String name;
    private String fullName;
    private String description;
    private String language;
    private int forksCount;
    private int watchersCount;
    private int stargazersCount;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("default_branch")
    private String defaultBranch;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("pushed_at")
    private String pushedAt;

    @SerializedName("owner")
    private Owner owner;

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public int getForksCount() {
        return forksCount;
    }
    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public int getWatchersCount() {
        return watchersCount;
    }
    public void setWatchersCount(int watchersCount) {
        this.watchersCount = watchersCount;
    }
    public int getStargazersCount() {
        return stargazersCount;
    }
    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }
    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPushedAt() {
        return pushedAt;
    }
    public void setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
    }

    public Owner getOwner() {
        return owner;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public static class Owner {
        private String login;

        @SerializedName("avatar_url")
        private String avatarUrl;

        @SerializedName("html_url")
        private String htmlUrl;

        public String getLogin() {
            return login;
        }
        public void setLogin(String login) {
            this.login = login;
        }
        public String getAvatarUrl() {
            return avatarUrl;
        }
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }
        public String getHtmlUrl() {
            return htmlUrl;
        }
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }
    }
}
