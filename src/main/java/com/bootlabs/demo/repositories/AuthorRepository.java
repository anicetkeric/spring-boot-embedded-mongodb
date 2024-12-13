
package com.bootlabs.demo.repositories;

import com.bootlabs.demo.document.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * <h2>AuthorRepository</h2>
 * Description: Spring Data repository for the {@link Author} entity.
 * 
 * @author @bootteam
 */
@Repository
public interface AuthorRepository  extends MongoRepository<Author, String> {

}
