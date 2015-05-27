/**
 * 
 */
package io.pivotal.fe.pcf.sample.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lshannon
 *
 */
@Controller
public class SimpleDataController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${insert.message}")
	private String insert;
	
	@Value("${db.message}")
	private String db_create;
	
	@Value("${schema.message}")
	private String schema_create;
	
	@Value("${select.message}")
	private String select;
	
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	    jdbcTemplate.execute(schema_create);
	}
	

	//locally test like this: http://127.0.0.1:8080/set?message=beans
	@RequestMapping(value="/set", method=RequestMethod.GET)
    public @ResponseBody String setMessage(@RequestParam(value="message", required=true) String message) {
		jdbcTemplate.update(insert, message);
        return "Inserted Message into DB: " + message;
    }
	
	//http://127.0.0.1:8080/get
	@RequestMapping(value="/get", method=RequestMethod.GET)
    public @ResponseBody String getMessages() {
		List<String> messages = this.jdbcTemplate.query(
		       select,
		        new RowMapper<String>() {
		            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		                String message = new String(rs.getString("message"));
		                return message;
		            }
		        });
        return messages.toString();
    }

}
