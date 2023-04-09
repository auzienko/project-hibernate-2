package com.javarush.hibernate2;

import com.javarush.hibernate2.entity.*;
import com.javarush.hibernate2.repository.impl.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("ALL")
public class Runner {
    private static SessionFactory sessionFactory;
    private static CityCrudRepository crudCityRepository;
    private static FilmCrudRepository crudFilmRepository;
    private static ActorCrudRepository crudActorRepository;
    private static StaffCrudRepository crudStaffRepository;
    private static StoreCrudRepository crudStoreRepository;
    private static RentalCrudRepository crudRentalRepository;
    private static AddressCrudRepository crudAddressRepository;
    private static CountryCrudRepository crudCountryRepository;
    private static PaymentCrudRepository crudPaymentRepository;
    private static CategoryCrudRepository crudCategoryRepository;
    private static CustomerCrudRepository crudCustomerRepository;
    private static FilmTextCrudRepository crudFilmTextRepository;
    private static LanguageCrudRepository crudLanguageRepository;
    private static InventoryCrudRepository crudInventoryRepository;

    public static void main(String[] args) {
        init();
        {
            Customer customer = createCustomer();

            returnInventory();

            customerRentInventory(customer);

            newFilmMade();
        }
        finish();
    }

    private static void init() {
        initSessionFactory();
        initRepos(sessionFactory);
    }

    private static void finish() {
        System.out.println("Success");
    }

    private static void customerRentInventory(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Film film = crudFilmRepository.getAnyUnreturnedRental();
            Store store = crudStoreRepository.getItems(0, 1).get(0);
            Inventory inventory = new Inventory()
                    .setFilm(film)
                    .setStore(store);
            crudInventoryRepository.save(inventory);

            Staff staff = store.getManagerStaff();

            Rental rental = new Rental()
                    .setStaff(staff)
                    .setCustomer(customer)
                    .setInventory(inventory)
                    .setRentalDate(LocalDateTime.now());
            crudRentalRepository.save(rental);

            Payment payment = new Payment()
                    .setStuff(staff)
                    .setRental(rental)
                    .setCustomer(customer)
                    .setPaymentDate(LocalDateTime.now())
                    .setAmount(BigDecimal.valueOf(42.0));
            crudPaymentRepository.save(payment);

            session.getTransaction().commit();
        }
    }

    private static void returnInventory() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Rental rental = crudRentalRepository.getAnyUnreturnedRental();
            rental.setReturnDate(LocalDateTime.now());
            crudRentalRepository.save(rental);

            session.getTransaction().commit();
        }
    }

    private static Customer createCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Store store = crudStoreRepository.getItems(0, 1).get(0);

            City city = crudCityRepository.getItems(0, 1).get(0);

            Address address = new Address()
                    .setCity(city)
                    .setDistrict("Central")
                    .setPhone("000-1234567890")
                    .setAddress("Lenina str, 21");
            crudAddressRepository.save(address);

            Customer customer = new Customer()
                    .setAddress(address)
                    .setStore(store)
                    .setIsActive(true)
                    .setLastName("Lee")
                    .setFirstName("Mike")
                    .setEmail("m.lee@mlee.gov");
            crudCustomerRepository.save(customer);

            session.getTransaction().commit();
            return customer;
        }
    }

    private static void newFilmMade() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Language language = crudLanguageRepository.getItems(0, 1).get(0);
            Set<Category> categories = new HashSet<>(crudCategoryRepository.getItems(0, 2));
            Set<Actor> actors = new HashSet<>(crudActorRepository.getItems(0, 5));

            String title = "The Edge of Possibilities".toUpperCase();
            String description =
                    "This is a story about a young, ambitious woman " +
                            "who decides to start over in a new city. " +
                            "But her life takes an unexpected turn when she meets a mysterious man" +
                            " who offers her incredible opportunities and " +
                            "invites her to play a game where she can get everything she wants. " +
                            "But how far is she willing to go for her dreams? " +
                            "And what consequences await her on this path?";


            Film film = new Film()
                    .setTitle(title)
                    .setActors(actors)
                    .setRating(Rating.R)
                    .setLanguage(language)
                    .setLength((short) 10)
                    .setCategories(categories)
                    .setReleaseYear(Year.now())
                    .setRentalDuration((byte) 2)
                    .setDescription(description)
                    .setOriginalLanguage(language)
                    .setRentalRate(BigDecimal.valueOf(21.99))
                    .setSpecialFeatures(Set.of(Feature.TRAILERS))
                    .setReplacementCost(BigDecimal.valueOf(20.20));
            crudFilmRepository.save(film);

            FilmText filmText = new FilmText()
                    .setFilm(film)
                    .setTitle(title)
                    .setId(film.getId())
                    .setDescription(description.substring(0, 128));
            crudFilmTextRepository.save(filmText);

            session.getTransaction().commit();
        }
    }

    private static void initSessionFactory() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Inventory.class)
                .buildSessionFactory();
    }

    private static void initRepos(SessionFactory sessionFactory) {
        crudCityRepository = new CityCrudRepository(sessionFactory);
        crudFilmRepository = new FilmCrudRepository(sessionFactory);
        crudActorRepository = new ActorCrudRepository(sessionFactory);
        crudStaffRepository = new StaffCrudRepository(sessionFactory);
        crudStoreRepository = new StoreCrudRepository(sessionFactory);
        crudRentalRepository = new RentalCrudRepository(sessionFactory);
        crudAddressRepository = new AddressCrudRepository(sessionFactory);
        crudCountryRepository = new CountryCrudRepository(sessionFactory);
        crudPaymentRepository = new PaymentCrudRepository(sessionFactory);
        crudCategoryRepository = new CategoryCrudRepository(sessionFactory);
        crudCustomerRepository = new CustomerCrudRepository(sessionFactory);
        crudFilmTextRepository = new FilmTextCrudRepository(sessionFactory);
        crudLanguageRepository = new LanguageCrudRepository(sessionFactory);
        crudInventoryRepository = new InventoryCrudRepository(sessionFactory);
    }

}
