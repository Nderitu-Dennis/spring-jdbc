# spring-jdbc

- get the documentation [here](https://docs.spring.io/spring-framework/docs/2.5.5/reference/jdbc.html)

# NOTES

# Spring JDBC Template Approaches - Key Notes

## Main Template Options

### **JdbcTemplate** (Classic approach)
- Most widely used, "lowest level" approach
- All other approaches use JdbcTemplate under the covers
- Works with JDK 1.4+
- Handles resource management automatically (connections, statements)
- Translates SQLExceptions to Spring's DataAccessException hierarchy

### **NamedParameterJdbcTemplate**
- Wraps JdbcTemplate
- Uses named parameters (`:paramName`) instead of `?` placeholders
- Better documentation and easier to use with multiple parameters
- Works with JDK 1.4+

### **SimpleJdbcTemplate** (Java 5+)
- Combines features of JdbcTemplate and NamedParameterJdbcTemplate
- Uses Java 5 features: varargs, autoboxing, generics
- Cleaner, more concise syntax
- Requires JDK 5+

### **SimpleJdbcInsert/SimpleJdbcCall** (Java 5+)
- Uses database metadata to minimize configuration
- Only need to provide table/procedure name and parameter Map
- Works with SimpleJdbcTemplate
- Requires JDK 5+ and database with adequate metadata support

### **RDBMS Objects** (MappingSqlQuery, SqlUpdate, StoredProcedure)
- Object-oriented approach with reusable, thread-safe objects
- Define query, declare parameters, compile once
- Execute multiple times with different parameter values
- Modeled after JDO Query pattern
- Works with JDK 1.4+

## Key Benefits
- **Automatic resource management** - no need to manually close connections
- **Exception translation** - converts SQLExceptions to unchecked exceptions
- **Thread-safe** - JdbcTemplate instances can be shared
- **Mix and match** - can combine different approaches as needed

# JdbcTemplate - Core JDBC Processing & Error Handling

## What JdbcTemplate Does

**JdbcTemplate** is the central class for Spring JDBC operations. It simplifies JDBC by:
- **Handling resource management** - automatically creates and releases connections, statements, and ResultSets
- **Preventing common errors** - eliminates need to manually close connections
- **Managing workflow** - handles statement creation and execution; you provide SQL and extract results
- **Translating exceptions** - converts SQLExceptions to Spring's generic `org.springframework.dao` exception hierarchy (unchecked exceptions)

## Key Responsibilities

JdbcTemplate executes:
- SQL queries
- Update statements (INSERT, UPDATE, DELETE)
- Stored procedure calls
- ResultSet iteration and parameter value extraction

## Callback Interfaces

Code using JdbcTemplate implements callback interfaces with clearly defined contracts:

| Interface | Purpose |
|-----------|---------|
| **PreparedStatementCreator** | Creates a PreparedStatement given a Connection, providing SQL and parameters |
| **CallableStatementCreator** | Creates a CallableStatement for stored procedures |
| **RowCallbackHandler** | Extracts values from each row of a ResultSet |

## Configuration Options



### Spring IoC Container (Recommended over direct instantiation that's not covered here)
- Configure DataSource as a bean
```java
@Configuration  
public class DataSourceConfiguration {
	@Bean  

	JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
```    
- Inject JdbcTemplate into DAOs as a bean reference
```java
@Repository
public class CountryDaoImpl implements CountryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public List<Country> getAllCountries(){
		String sql = "SELECT id, name FROM country";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Country.class));
	}
}
```
- Call it from the controller:_notice that here we've calling the interface but Spring will inject its implementation_, `CountryDaoImpl`
```java
@Controller
public class MainController {

	@Autowired
	private CountryDao countryDao;
	

	@GetMapping("/test")
	public String getForm() {
		List<Country> countries = countryDao.getAllCountries();
		System.out.println("**countries** " + countries);
		return "demo";  //demo.jsp

	}
}
```
- Allows sharing a single thread-safe instance across multiple DAOs

## Important Notes

- **Thread-safe** - once configured, instances are thread-safe and can be shared
- **Stateful but not conversational** - maintains DataSource reference but no session state
- **SQL logging** - all SQL is logged at DEBUG level under the template's fully qualified class name
- **No need for multiple instances** - unless accessing multiple databases with different DataSources
