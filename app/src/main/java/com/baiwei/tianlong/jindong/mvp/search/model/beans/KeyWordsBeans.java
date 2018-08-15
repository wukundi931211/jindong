package com.baiwei.tianlong.jindong.mvp.search.model.beans;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "keywords")
public class KeyWordsBeans {
    @Id(autoincrement = true)
    private Long id;
    private String keywords;
    public String getKeywords() {
        return this.keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1153131406)
    public KeyWordsBeans(Long id, String keywords) {
        this.id = id;
        this.keywords = keywords;
    }
    @Generated(hash = 278393344)
    public KeyWordsBeans() {
    }
}
