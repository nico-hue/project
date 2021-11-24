package main.test;

import main.connection.ConnectionFactory;
import main.dao.CardDAO;
import main.dao.impl.CardDAOImpl;
import main.entity.Card;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import main.service.CardService;
import java.util.List;
import static org.testng.Assert.assertEquals;


public class CardServiceTest {

    private final ConnectionFactory factory = new ConnectionFactory();
    private final CardDAO dao = new CardDAOImpl(factory);
    private final CardService cardService = new CardService(dao);

    private static void assertCard(Card card, int cardId, String number, double balance) {
        assertEquals(cardId, card.getCardId());
        assertEquals(number, card.getNumber());
        assertEquals(balance, card.getBalance());
    }

    @BeforeMethod
    public void setUp() {
        Card card = new Card();
        card.setCardId(9);
        card.setNumber("9999");
        card.setBalance(9.0);
        dao.add(card);
    }

    @AfterMethod
    public void tearDown() {
        dao.deleteById(9);
    }


    @Test
    public void testGetByNumber() {

        Card cs = cardService.getByNumber("9999");

        Assert.assertEquals(9, cs.getCardId());
        Assert.assertEquals("9999", cs.getNumber());
        Assert.assertEquals(9.0, cs.getBalance());
    }

    @Test
    public void testGetAll() {

        List<Card> cardsList = cardService.getAll();
        int size = cardsList.size();

        Card card1 = new Card();
        card1.setCardId(8);
        card1.setNumber("8888");
        card1.setBalance(8.1);
        dao.add(card1);

        List<Card> cardsList1 = cardService.getAll();
        int delta = cardsList1.size() - size;

        assertEquals(1, delta);
        dao.deleteById(8);
    }

    @Test
    public void testGetById() {
        assertCard(cardService.getById(9), 9, "9999", 9.0);
    }

    @Test
    public void testUpdate() {

        Card newCard = new Card();
        newCard.setCardId(9);
        newCard.setNumber("8888");
        newCard.setBalance(8.1);
        dao.add(newCard);
        assertCard(cardService.getById(9), 9, "9999", 9.0);
        cardService.update(newCard);
        assertCard(cardService.getById(9), 9, "8888", 8.1);
    }

    @Test
    public void testDeleteById() {

        Card card1 = new Card();
        card1.setCardId(8);
        card1.setNumber("8888");
        card1.setBalance(8.1);
        dao.add(card1);

        cardService.deleteById(8);
        Card cs  = dao.getById(8);

        Assert.assertNull(cs);
    }

    @Test
    public void testAdd() {

        Card card1 = new Card();
        card1.setCardId(8);
        card1.setNumber("8888");
        card1.setBalance(8.1);
        dao.add(card1);

        assertCard(cardService.getById(8), 8, "8888", 8.1);

    }
}
