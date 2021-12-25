package com.tobiakindele.parceldelivery.utils;

import javax.faces.model.DataModel;

/**
 *
 * @author oyindamolaakindele
 * @param <T>
 */
public abstract class PaginationHelper<T> {
    
    private int pageSize;
    private int page;
    
    public PaginationHelper(int pageSize){
        this.pageSize = pageSize;
    }
    
    public abstract int getCount();
    
    public abstract DataModel<T> createPageDataModel();
    
    public int getPageFirstItem(){
        return page * pageSize;
    }
    
    public int getPageLastItem(){
        int i = getPageFirstItem() + pageSize - 1;
        int count = getCount() - 1;
        if(i > count) {
            i = count;
        }
        if(i < 0) {
            i = 0;
        }
        return i;
    }
    
    public boolean isHasNextPage() {
        return (page + 1) * pageSize + 1 <= getCount();
    }
    
    public void nextPage(){
        if(isHasNextPage()) {
            page++;
        }
    }
    
    public boolean isHasPreviousPage() {
        return page > 0;
    }
    
    public void previousPage() {
        if(isHasPreviousPage()) {
            page--;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
