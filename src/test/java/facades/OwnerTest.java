package facades;

import entities.Owner;
import utils.EMF_Creator;
import entities.RenameMe;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class OwnerTest {

    private static EntityManagerFactory emf;
    private static OwnerFacade facade;
    private static Owner owner;

    public OwnerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = OwnerFacade.getOwnerFacade(emf);
    }

   
    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        owner = new Owner("Vindervej", "Vindermand", 12345678); 
        try {
            em.getTransaction().begin();
            em.createNamedQuery("RenameMe.deleteAllRows").executeUpdate();
            em.persist(owner);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    
    @Test
    public void testGetOwners1() throws Exception {
        
        assertEquals (1, facade.getAllOwners1().size(), "der skal kun være -->");
    }
    
    

}