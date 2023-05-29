package shop;
public class Book {
    private int id;

    private String name;
    private String author;
    private double price;
    private String category;
    private String desc;
    private int amount;
    private String img;


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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Book(int id, String name, String author, double price, String category, String desc, int amount, String img) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.category = category;
        this.desc = desc;
        this.amount = amount;
        this.img = img;
    }
}


