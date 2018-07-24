
package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDB {
    
    
    public static boolean userAuth(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM users WHERE userID = " + user.getUserid();
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery(query);            
            
            while (rs.next()) {
                user.setPassword(rs.getInt("userPassword"));
            }            
            
            if (user.isAuthenticated()) {
                return true;
            } else {
                return false;
            }               
                
            } catch (SQLException e) {
                System.out.println(e);
                return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static User userInfo(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM users " +
                        "WHERE userID = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUserid());
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setAdminlevel(rs.getString("adminLevel"));
                user.setStoreid(Integer.parseInt(rs.getString("storeID")));
                user.setUserid(rs.getString("userID"));
                user.setUsername(rs.getString("userName"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;            
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        
        
    }
    
    public static ArrayList<Store> storeList() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Store> stores = new ArrayList<>();
        
        String query = "SELECT * FROM stores ORDER BY StoreName ";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                Store st = new Store();
                st.setStoreaddr(rs.getString("storeAddr"));
                st.setStoreemp(Integer.parseInt(rs.getString("storeEmp")));
                st.setStoreid(Integer.parseInt(rs.getString("storeID")));
                st.setStorename(rs.getString("storeName"));
                stores.add(st);
            }
            return stores;
        } catch (SQLException e) {
            System.out.println(e);
            return null;            
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Book> storeInv(String storeid) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Book> books = new ArrayList<>();
        
        String query = "SELECT bookinv.storeID, bookinv.bookID, title, price, bookinv.OnHand, author " +
                        "FROM bookinv, booklist " +
                        "WHERE bookinv.bookID = booklist.bookID and bookinv.storeID = ? " +
                        "ORDER BY bookID";
        
         try {
            ps = connection.prepareStatement(query);
            ps.setString(1, storeid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book bookentry = new Book();
                bookentry.setStoreid(Integer.parseInt(rs.getString("storeID")));
                bookentry.setBookcd(rs.getString("bookID"));
                bookentry.setTitle(rs.getString("title"));
                bookentry.setPrice(Double.parseDouble(rs.getString("Price")));
                bookentry.setQty(Integer.parseInt(rs.getString("OnHand")));
                bookentry.setAuthor(rs.getString("author"));
                books.add(bookentry);                
            }
            return books;
        } catch (SQLException e) {
            System.out.println(e);
            return null;            
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static void updateInventory(String bookcd, String storeid, String bookqty) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE bookinv SET onhand = ? WHERE storeID = ? and bookID = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, bookqty);
            ps.setString(2, storeid);
            ps.setString(3, bookcd);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
