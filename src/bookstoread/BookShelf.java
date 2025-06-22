package bookstoread;

import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class BookShelf {
    private final List<Book> books = new ArrayList<>();

    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    public void add(Book... booksToAdd) {
        Arrays.stream(booksToAdd).forEach(books::add);
    }

    public List<Book> arrange() {
        return  arrange(Comparator.naturalOrder());
    }

    public List<Book> arrange(Comparator<Book> criteria) {
        return books.stream().sorted(criteria).collect(Collectors.toList());
    }

    public Map<Year, List<Book>> groupByPublicationYear() {
        return  this.groupBy(book ->
                Year.of(book.getPublishedOn().getYear()));
    }

    public <K> Map<K, List<Book>> groupBy(Function<Book, K> fx) {
        return books.stream().collect(Collectors.groupingBy(fx));
    }

    // Methode qui retourne une liste de livres triée par date de publication croissante (du plus ancien au plus récent).
    public List<Book> booksByPublicationDate() {
        return books.stream()
                .sorted(Comparator.comparing(Book::getPublishedOn))
                //.collect(Collectors.toList());
                .toList();
    }

}