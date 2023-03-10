package com.example.unit10maven.service;



import com.example.unit10maven.Unit10MavenApplication;
import com.example.unit10maven.data.*;
import com.example.unit10maven.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Unit10MavenApplication.class)
public class BookServiceImplTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorOfBookRepository authorOfBookRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PurchasedBookRepository purchasedBookRepository;

    @Autowired
    private PriceCalcRepository priceCalcRepository;

    @Autowired
    private BookService bookService;

    @Before
    public void init() {

        Author mark = new Author();
        mark.setFirstname("Mark");
        mark.setLastname("Twain");

        authorRepository.save(mark);

        Author jules = new Author();
        jules.setFirstname("Jules");
        jules.setLastname("Verne");
        authorRepository.save(jules);



        Book book = new Book();
        book.setDescription("Увлекательные приключения Тома Сойера");
        book.setTitle("Приключения Тома Сойера");
        book.setYears(1876);
        bookRepository.save(book);

        AuthorOfBook aob1 = new AuthorOfBook();
        aob1.setAuthor(mark);
        aob1.setBook(book);
        authorOfBookRepository.save(aob1);

        Book book2 = new Book();
        book2.setTitle("Михаил Строгов");
        book2.setYears(1876);
        bookRepository.save(book2);

        AuthorOfBook aob2 = new AuthorOfBook();
        aob2.setAuthor(mark);
        aob2.setBook(book2);
        authorOfBookRepository.save(aob2);

        AuthorOfBook aob3 = new AuthorOfBook();
        aob3.setAuthor(jules);
        aob3.setBook(book2);
        authorOfBookRepository.save(aob3);

        Client client1 = new Client();
        client1.setAddress("Lobnya street");
        client1.setName("Garik");
        clientRepository.save(client1);


        PurchasedBook purchasedBook1= new PurchasedBook();
        purchasedBook1.setBook(book);
        purchasedBook1.setClient(client1);
        purchasedBook1.setPrice(BigDecimal.valueOf(1000) );
        purchasedBookRepository.save(purchasedBook1);

        PurchasedBook purchasedBook2= new PurchasedBook();
        purchasedBook2.setBook(book);
        purchasedBook2.setClient(client1);
        purchasedBook2.setPrice(BigDecimal.valueOf(1500) );
        purchasedBookRepository.save(purchasedBook2);



        PurchasedBook purchasedBook3= new PurchasedBook();
        purchasedBook3.setBook(book2);
        purchasedBook3.setClient(client1);
        purchasedBook3.setPrice(BigDecimal.valueOf(2500) );
        purchasedBookRepository.save(purchasedBook3);



    }

    @Test
    public void testCreation() {
        boolean founded = false;
        for (Book book : bookRepository.findAll()) {
            if (book.getTitle().contains("Тома Сойера")) {
                founded = true;
            }

        }
        assertTrue(founded);

    }

    @Test
    public void testFindByYears() {
        assertEquals(2, bookRepository.findBooksByYears(1876).size());
        assertEquals(0, bookRepository.findBooksByYears(1878).size());
    }

    @Test
    public void testPaging() {

        System.out.println(bookService.findAtPage(1, 1,
                Sort.Direction.ASC, "title")
                .get().findFirst());

        boolean founded = bookService.findAtPage(1, 1,
                Sort.Direction.ASC, "title")
                .get().anyMatch(book ->
                book.getTitle().equals("Приключения Тома Сойера"));

        assertTrue(founded);
    }

    @Test
    public void findSame() {
        Book book = new Book();
        book.setYears(1876);
        assertEquals(2, bookService.findSame(book).size());
    }

    @Test
    public void findInRange() {
        assertEquals(0,
                bookRepository.findAll(
                        BooksSpecification.yearsInRange(1874, 1876))
                        .size());

        assertEquals(2, bookRepository.findAll(BooksSpecification.yearsInRange(1874, 1877)).size());


    }

    @Test
    public void findByAuthorLastName() {
        assertEquals(2, bookRepository.findByAuthorLastname("Twain").size());
        assertEquals(1, bookRepository.findByAuthorLastname("Verne").size());

    }

    @Test
    public void testComplexQuery() {
        System.out.println(bookRepository.complexQueryMethod());
    }

    @Test
    public void testPurchaseBook() {


        System.out.println("------------------------------------------------------------");
/*
* BigDecimal a = new BigDecimal("100")
BigDecimal b = new BigDecimal("100.00")
assertThat(a).isEqualByComparingTo(b);
* */
        Book book = bookRepository.findByYearsAndTitle(1876,"Приключения Тома Сойера");
        System.out.println("My book id is "+book);
        System.out.println(""+bookRepository.getSumPriceByBook(book));
        assertThat(BigDecimal.valueOf(2500.000)).isEqualByComparingTo(bookRepository.getSumPriceByBook(book));
        //assertEquals(BigDecimal.valueOf(2500.000), bookRepository.getSumPriceByBook(book));

        Client client=clientRepository.findByName("Garik");
        System.out.println("My client is " +client);
        System.out.println(""+bookRepository.getSumPriceByClient(client));

    }

}
