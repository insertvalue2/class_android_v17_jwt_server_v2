package com.koreait.myjwtapp.interfaces;

import com.koreait.myjwtapp.repository.models.response.common.Data;

public interface OnBlogListRefresh {
    void refresh(String mag);
    void movePage(Data data);
}
