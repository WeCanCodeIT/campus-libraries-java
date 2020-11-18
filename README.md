# Campus Libraries

A workshop project for module 5.

## Entities
- `Campus`
  - `String location`
  - `String techStack`
  - `Set<Book> books`
- `Book`
  - `String title`
  - `Campus campus`
  - `Set<Author> authors`
  - `String description`
  - `Boolean available`
- `Author`
  - `String name`
  - `String bio`
  - `Set<Book> books`
 
 ## Tests Todo
 - [ ] Integration Tests
   - [ ] `JpaWiringTest`
   - [ ] `HttpRequestTest`
   - [ ] `SpringApplicationTest`
   - [ ] `WebLayerTest`
 - [ ] Unit Test Line Coverage 90%+
 
 ## Endpoints
 | Priority | HTTP Method | Endpoint | Resource | Idempotent |
 | -------- | ----------- | -------- | -------- | ---------- | 
 | * | `GET` | `/campuses`| Get all campuses | TRUE |
 | * | `GET` | `/campuses/{id}` | Get campus for given id | TRUE |
 | * | `POST` | `/campuses` | Create new campuses | FALSE |
 |  | `PUT` | `/campuses/{id}` | Update campus for given id | TRUE |
 | * | `PATCH` | `/campuses/{id}/books` | Create new book resource associated with campus for given id | FALSE |
 | * | `GET` | `/books` | Get all books | TRUE |
 | * | `GET` | `/books/{id}` | Get book for given id | TRUE |
 |  | `POST` | `/books` | Create new book | FALSE |
 |  | `PUT` | `/books/{id}` | Update book for given id | TRUE |
 | * | `GET` | `/authors` | Get all authors | TRUE | FALSE |
 | * | `GET` | `/authors/{id}` | Get author for given id | TRUE |
 | * | `POST` | `/authors` | Create new author | FALSE |
 |  | `PUT` | `/authors/{id}` | Update author for given id | TRUE |
 |  | `PATCH` | `/authors/{id}/name` | Update author name for author with given id | TRUE |



 Wednesday Todo List

 - Get campuses from API on load.
 - Post a new campus
 - Patch a new book to campus
