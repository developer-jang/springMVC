package com.example.springmvc.demo.vo;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(exclude = "pageList")
public class PageMakerVO {

    private Page result;

    private Pageable prevPage;
    private Pageable nextPage;
    private Pageable currentPage;

    private int currentPageNumber;
    private int totalPageNumber;

    private List<Pageable> pageList;

    public PageMakerVO(Page result) {
        this.result = result;
        this.currentPage = result.getPageable();
        this.currentPageNumber = currentPage.getPageNumber();
        this.totalPageNumber = result.getTotalPages();
        this.pageList = new ArrayList<>();
        calcPages();
    }

    private void calcPages() {
        int tempEndPageNumber = (int) (Math.ceil(this.currentPageNumber/10.0)*10);
        int startNumber = tempEndPageNumber - 9;

        Pageable startPage = this.currentPage;

        for(int i=startNumber; i < this.currentPageNumber; i++) {
            startPage = startPage.previousOrFirst();
        }
        this.prevPage = startPage.getPageNumber() <=0 ? null : startPage.previousOrFirst();

        if(this.totalPageNumber < tempEndPageNumber) {
            tempEndPageNumber = this.totalPageNumber;
            this.nextPage = null;
        }

        for(int i = startNumber; i <=tempEndPageNumber; i++) {
            pageList.add(startPage);
            startPage = startPage.next();
        }
        this.nextPage = startPage.getPageNumber() + 1 < totalPageNumber ? startPage : null;
    }

}
