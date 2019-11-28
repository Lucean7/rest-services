package ua.com.epam.entity.book.nested;

public class Additional {
    private Long pageCount;
    private Size size;

    public Additional() {
    }

    public Additional(Long pageCount, Size size) {
        this.pageCount = pageCount;
        this.size = size;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
