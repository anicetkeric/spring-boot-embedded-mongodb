
package com.bootlabs.demo.repositories;

import com.bootlabs.demo.document.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * <h2>BookRepository</h2>
 * Description: Spring Data repository for the {@link Book} entity.
 * 
 * @author @bootteam
 */
@Repository
public interface BookRepository extends MongoRepository<Book, String> {

}
