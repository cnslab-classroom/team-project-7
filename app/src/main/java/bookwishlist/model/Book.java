package bookwishlist.model;

public class Book{
    private long id; 
    private String title; 
    private String author; 

    public Book(String title, String author) {
       
        this.title = title;
        this.author = author;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String toString() {
        return "Book{" +
                "id= " + id +
                ", 제목 = '" + title + '\'' +
                ", 저자 = '" + author + '\'' +
                '}';
    }
}

