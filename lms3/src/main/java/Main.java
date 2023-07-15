import com.mysql.jdbc.log.NullLogger;

import java.sql.*;
import java.util.Scanner;

/*图书管理系统*/
public class Main {
    //入口函数
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //读取控制台输入的对象
        Scanner scanner = new Scanner(System.in);

        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");//引入类，maven引入了jar文件
        //2.获取连接对象
        String url = "jdbc:mysql://127.0.0.1:3308/tb_user?useSSL-false&characterEncoding=utf-8";//url为本机ip地址下的端口号为3308的名为tb_user的数据库
        Connection connection = DriverManager.getConnection(url, "root", "123456");//connection是一个Connection对象
        // 3. 获取执行SQL的对象
        Statement statement = connection.createStatement();
        /*conn.createStatement() 是用于创建一个 Statement 对象，它是执行 SQL 语句的一种方式之一。
        * 通过调用它的 createStatement() 方法，我们可以创建一个与该连接相关联的 Statement 对象。*/
        /* Statement 接口提供了执行 SQL 语句的方法，比如 executeQuery() 用于执行查询语句并返回结果集，executeUpdate() 用于执行 INSERT、UPDATE 或 DELETE 语句。*/


        while (true) {
            //1.根据账号和密码进行登录
            System.out.println("**********欢迎访问图书管理系统**********");
            String account = null;
            String password = null;
            System.out.print("请输入账号:");
            account = scanner.nextLine();
            System.out.print("请输入密码:");
            password = scanner.nextLine();

            //2.根据账号和密码在数据库表中tb_user中查询---拼接sql
            String sql = "select * from tb_user where account = '" + account + "' and password = '" + password + "';";
            // 3. 执行SQL并获取结果
            ResultSet result = statement.executeQuery(sql);

            if (result.next()) {
                System.out.println("姓名为【" + result.getString(2) + "】的用户，登录成功！");
                break;//跳出当前while循环
            } else {
                System.out.println("账号或密码错误!");
            }
        }

        while (true) {
            // 是否退出系统
            boolean isExitSystem = false;
            System.out.println("**********************************************");
            System.out.println("* 1. 人员管理");
            System.out.println("* 2. 图书管理");
            System.out.println("* 3. 借阅管理");
            System.out.println("* 4. 退出登录");
            System.out.println("**********************************************");
            System.out.print("请输入指令：");
            String input = scanner.nextLine();
            switch (input) {
                case "1": // 人员管理：增删改查
                    while (true) {
                        System.out.println("**********************************************");
                        System.out.println("* 1. 新增人员");
                        System.out.println("* 2. 删除人员");
                        System.out.println("* 3. 修改人员");
                        System.out.println("* 4. 查询人员");
                        System.out.println("* 5. 返回上一级");
                        System.out.println("* 6. 退出登录");
                        System.out.println("**********************************************");
                        System.out.print("请输入指令：");
                        input = scanner.nextLine();

                        // 是否返回上一级
                        boolean isGoBack = false;

                        switch (input) {
                            case "1": // 新增人员
                                System.out.print("请输入姓名：");
                                String addUserName = scanner.nextLine();
                                System.out.print("请输入性别：");
                                String addUserGender = scanner.nextLine();
                                System.out.print("请输入年龄：");
                                int addUserAge = Integer.valueOf(scanner.nextLine()); // Integer.valueOf() ==> 可以将字符串转换成整型
                                System.out.print("请输入账号：");
                                String addUserAccount = scanner.nextLine();
                                System.out.print("请输入密码：");
                                String addUserPassword = scanner.nextLine();

                                String addUserSQL = "insert into tb_user values(null, '" + addUserName + "', '" + addUserGender + "', " + addUserAge + ", '" + addUserAccount + "', '" + addUserPassword + "');";

                                statement.executeUpdate(addUserSQL);
                                System.out.println("新增成功！");
                                break;
                            case "2": // 删除人员
                                System.out.print("请输入要删除人员的ID：");
                                String deleteUserId = scanner.nextLine();

                                String deleteUserSQL = "delete from tb_user where id = " + deleteUserId;

                                statement.executeUpdate(deleteUserSQL);
                                System.out.println("删除成功！");
                                break;
                            case "3": // 修改人员
                                System.out.print("请输入ID：");
                                String updateUserId = scanner.nextLine();
                                System.out.print("请输入姓名：");
                                String updateUserName = scanner.nextLine();
                                System.out.print("请输入性别：");
                                String updateUserGender = scanner.nextLine();
                                System.out.print("请输入年龄：");
                                int updateUserAge = Integer.valueOf(scanner.nextLine()); // Integer.valueOf() ==> 可以将字符串转换成整型
                                System.out.print("请输入账号：");
                                String updateUserAccount = scanner.nextLine();
                                System.out.print("请输入密码：");
                                String updateUserPassword = scanner.nextLine();

                                String updateUserSQL = "update tb_user set name = '" + updateUserName + "', gender = '" + updateUserGender + "', age = " + updateUserAge + ", account = '" + updateUserAccount + "', password = '" + updateUserPassword + "' where id = " + updateUserId + ";";

                                statement.executeUpdate(updateUserSQL);
                                System.out.println("修改成功！");
                                break;
                            case "4": // 查询人员
                                ResultSet selectUsersResult = statement.executeQuery("select * from tb_user;");
                                System.out.println("数据库中用户信息如下：");
                                while (selectUsersResult.next()) {
                                    System.out.println("ID：" + selectUsersResult.getInt(1)
                                            + "，姓名：" + selectUsersResult.getString(2)
                                            + "，性别：" + selectUsersResult.getString(3)
                                            + "，年龄：" + selectUsersResult.getInt(4)
                                            + "，账号：" + selectUsersResult.getString(5)
                                            + "，密码：" + selectUsersResult.getString(6));
                                }
                                break;
                            case "5": // 返回上一级
                                isGoBack = true;
                                break;
                            case "6": // 退出登录
                                isExitSystem = true;
                                break;
                            default:
                                System.out.println("输入有误，请重新输入！");
                        }//end_little_switch1

                        if (isGoBack) {
                            break;//跳出当前little_while1，进入大的switch内
                        }
                        if (isExitSystem) {
                            break;//跳出当前little_while1
                        }
                    }//end_little_while1
                    break;//跳出当前的大的switch
                case "2": // 图书管理：增删改查
                    while(true) {
                        System.out.println("**********************************************");
                        System.out.println("* 1. 新增图书");
                        System.out.println("* 2. 删除图书");
                        System.out.println("* 3. 修改图书");
                        System.out.println("* 4. 查询图书");
                        System.out.println("* 5. 返回上一级");
                        System.out.println("* 6. 退出登录");
                        System.out.println("**********************************************");
                        System.out.print("请输入指令：");
                        input = scanner.nextLine();

                        // 是否返回上一级
                        boolean isGoBack = false;
                        switch (input) {
                            case "1": // 新增图书
                                System.out.print("请输入图书类型：");
                                String addBookType = scanner.nextLine();
                                System.out.print("请输入图书名称：");
                                String addBookName = scanner.nextLine();
                                System.out.print("请输入作者名称：");
                                String addAuthorName = scanner.nextLine();
                                System.out.print("请输入出版社：");
                                String addBookPublish = scanner.nextLine();
                                System.out.print("请输入库存：");
                                int addBookNum = Integer.valueOf(scanner.nextLine());// Integer.valueOf() ==> 可以将字符串转换成整型

                                String addBookSQL = "insert into tb_information values(null, '" + addBookType + "', '" + addBookName + "', '" + addAuthorName + "', '" + addBookPublish + "',"+ addBookNum +");";

                                statement.executeUpdate(addBookSQL);
                                System.out.println("新增成功！");
                                break;
                            case "2": // 删除图书
                                System.out.print("请输入要删除图书的ID：");
                                String deleteBookId = scanner.nextLine();

                                String deleteBookSQL = "delete from tb_information where id = " + deleteBookId ;

                                statement.executeUpdate(deleteBookSQL);
                                System.out.println("删除成功！");
                                break;
                            case "3": // 修改图书----基本只考虑库存的增加和图书信息的改动
                                System.out.print("请输入图书号：");
                                String updateBookId = scanner.nextLine();
                                System.out.print("请输入图书类型：");
                                String updateBookType = scanner.nextLine();
                                System.out.print("请输入图书名称：");
                                String updateBookName = scanner.nextLine();
                                System.out.print("请输入作者名称：");
                                String updateAuthorName = scanner.nextLine();
                                System.out.print("请输入出版社：");
                                String updateBookPublish = scanner.nextLine();
                                System.out.print("请输入库存：");
                                String updateBookNum = scanner.nextLine();

                                String updateBookSQL = "update tb_information set type = '" + updateBookType + "', " +
                                        "name = '" + updateBookName + "', author = '" + updateAuthorName + "', " +
                                        "publish = '" + updateBookPublish + "', count = " + updateBookNum + " where id = " + updateBookId + ";";

                                statement.executeUpdate(updateBookSQL);
                                System.out.println("修改成功！");
                                break;
                            case "4": // 查询图书
                                ResultSet selectBooksResult = statement.executeQuery("select * from tb_information;");
                                System.out.println("数据库中图书信息如下：");
                                while (selectBooksResult.next()) { //循环打印图书表
                                    System.out.println("ID：" + selectBooksResult.getInt(1)
                                            + "，类型：" + selectBooksResult.getString(2)
                                            + "，图书名称：" + selectBooksResult.getString(3)
                                            + "，作者名称：" + selectBooksResult.getString(4)
                                            + "，出版社：" + selectBooksResult.getString(5)
                                            + "，库存：" + selectBooksResult.getInt(6));
                                }
                                break;
                            case "5": // 返回上一级
                                isGoBack = true;
                                break;
                            case "6": // 退出登录
                                isExitSystem = true;
                                break;
                            default:
                                System.out.println("输入有误，请重新输入！");
                        }//end_little_switch

                        if (isGoBack) {
                            break;//跳出当前little_while2，进入大的switch内
                        }
                        if (isExitSystem) {
                            break;//跳出当前little_while2
                        }
                    }//end_little_while2

                    break;//跳出当前biggest_switch,进入biggest_while

                case "3"://借阅管理
                    while(true) {
                        System.out.println("**********************************************");
                        System.out.println("* 1. 借书");//借书人，借书编号，借书日期,
                        System.out.println("* 2. 还书");//还书日期，归还该为1
                        System.out.println("* 3. 查看借书记录");
                        System.out.println("* 4. 返回上一级");
                        System.out.println("* 5. 退出登录");
                        System.out.println("**********************************************");
                        System.out.print("请输入指令：");
                        input = scanner.nextLine();
                        // 是否返回上一级
                        boolean isGoBack = false;

                        switch (input) {
                            case "1"://借书------一次借一本
                                //打印图书信息--查询图书信息------每次借书，剩余数量-1
                                System.out.println("********************************欢迎读者前来借书！********************************");
                                System.out.println("温馨提示：请在90天内按时还书");
                                ResultSet selectBooksResult = statement.executeQuery("select * from tb_information;");
                                System.out.println("数据库中图书信息如下：");
                                while (selectBooksResult.next()) { //循环打印图书表
                                    System.out.println("ID：" + selectBooksResult.getInt(1)
                                            + "，类型：" + selectBooksResult.getString(2)
                                            + "，图书名称：" + selectBooksResult.getString(3)
                                            + "，作者名称：" + selectBooksResult.getString(4)
                                            + "，出版社：" + selectBooksResult.getString(5)
                                            + "，库存：" + selectBooksResult.getInt(6));
                                }

                                int borrowUserId;
                                int borrowBookId;
                                String borrowBookDate = null;
                                String borrowUserPhone = null;

                                //输入校验
                                while(true){
                                System.out.println("请输入借书的相关信息：");
                                System.out.print("请输入借书人的id：");
                                borrowUserId = Integer.valueOf(scanner.nextLine());// Integer.valueOf() ==> 可以将字符串转换成整型
                                System.out.print("请输入要借的书的id：");
                                borrowBookId = Integer.valueOf(scanner.nextLine());// Integer.valueOf() ==> 可以将字符串转换成整型
                                System.out.print("请输入借书日期（例如2023-07-13）：");
                                borrowBookDate = scanner.nextLine();
                                System.out.print("请输入借书人的联系方式：");
                                borrowUserPhone = scanner.nextLine();


                                String query = "SELECT tb_information.id AS info_id, tb_user.id AS user_id "
                                                + "FROM tb_information "
                                                + "JOIN tb_user ON tb_information.id = tb_user.id "
                                                + "WHERE tb_information.id = " + borrowBookId + " AND tb_user.id = " + borrowUserId;
                                //使用 SQL 的联结（JOIN）操作来判断输入的两个 id 号在对应的数据库表中是否存在
                                 // 当查询两个表并使用联结操作时，我们使用 SQL 的 JOIN 关键字来组合表。在这个例子中，我们使用的是内联结（INNER JOIN），它会返回两个表中匹配的行。
                                ResultSet rs = statement.executeQuery(query);


                                    if (rs.next()) {//存在结果集
                                    System.out.println("输入的id存在于MySQL表中的id");
                                    break;
                                } else {//不存在结果集
                                    System.out.println("输入的id不存在于MySQL表中的id，请重新输入！");
                                }

                            }

                                String returnBookDate = null;
                                String borrowBookSQL = "insert into tb_borrowed values(null, " + borrowUserId + ", " + borrowBookId  + ", '" + borrowBookDate + "',null ,0,0,'"+ borrowUserPhone +"');";
                                String afterBorrowBookNumSql = "update tb_information set count = count - 1 where id = "+ borrowBookId +"";   //借书后数量--
                                statement.executeUpdate(borrowBookSQL);
                                statement.executeUpdate(afterBorrowBookNumSql);
                                System.out.println("借书成功！");
                                break;

                            case "2"://还书
                                //通过输入自己的id去搜索自己借书记录----打印---还未加进去

                                int returnUserId;//还书人id
                                int returnBookId;//还的书id
                                String returnDate = null;//还书日期
                                String afterReturnBookNumSql;//还书后的库存

                                while(true) {
                                    System.out.println("请输入还书的相关信息：");
                                    System.out.print("请输入还书人的id：");
                                    returnUserId = Integer.valueOf(scanner.nextLine());// Integer.valueOf() ==> 可以将字符串转换成整型
                                    System.out.print("要还的书的id:");
                                    returnBookId = Integer.valueOf(scanner.nextLine());// Integer.valueOf() ==> 可以将字符串转换成整型
                                    System.out.print("请输入还书日期（例如2023-09-13）：");
                                    returnDate = scanner.nextLine();


                                    String query = "SELECT * FROM tb_borrowed WHERE borrowed_id = " + returnBookId + " AND person_id = " + returnUserId + " AND give_back = 0";
                                    ResultSet rs = statement.executeQuery(query);

                                    if (rs.next()) {//输入的 returnBookId 和 returnUserId 存在于 tb_borrowed 表中且未还
                                        System.out.println("输入的 还书id 和 还书人的id 存在于借阅表中且未还");
                                        break;
                                    } else {//输入的 returnBookId 和 returnUserId 不存在于 tb_borrowed 表中或已还
                                        System.out.println("输入的 还书id 和 还书人的id 不存在于借阅表中或已还");
                                        System.out.println("请重新输入相关信息！");
                                    }
                                }


                                //更新tb_information中对应的库存数量
                                afterReturnBookNumSql = "update tb_information set count = count + 1 where id = " + returnBookId ;   //还书后数量++
                                statement.executeUpdate(afterReturnBookNumSql);//执行sql语句

                                String returnBookSQL = "update tb_borrowed set return_day ='"+returnDate+"';";
                                statement.executeUpdate(returnBookSQL);

                                //利用mySql函数去计算是否超出日期,若超出90天，则输出已超出，若等于负数，则输出你已按时归还，并标记是否超出期限

                                String a = "select datediff(return_day,borrowed_day) from tb_borrowed where person_id="+ returnUserId +" and borrowed_id = " + returnBookId;
                                ResultSet selectDatesResult = statement.executeQuery(a);//执行a语句
                                //DATEDIFF(date1,date2)--返回起始时间date1 和 结束时间date2之间的天数

                                int dateDiff = 0; // 声明一个整型变量用于保存日期差值

                                if (selectDatesResult.next()) {
                                    dateDiff = selectDatesResult.getInt(1);
                                }

                                System.out.println("您在"+dateDiff+"天后还书");//还书日期与借书日期的差值

                                if (dateDiff > 90) { //标志为已超出:1
                                    String isOverSql = "update tb_borrowed set over_day = 1 where borrowed_id = " + returnBookId;
                                    statement.executeUpdate(isOverSql);//执行isOverSql语句
                                    System.out.println("已超出期限还书！请信守承诺。");
                                }
                                else{
                                    System.out.println("感谢您在规定期限内还书！");
                                }

                                // 更新give_back为已归还
                                String b ="update tb_borrowed set give_back=1 where person_id= "+ returnUserId +";";
                                statement.executeUpdate(b);//执行isOverSql语句

                                System.out.println("还书成功！欢迎下次再来借书！");

                                break;

                            case "3":// 查看借书记录tb_borrowed
                                ResultSet selectBorrowResult = statement.executeQuery("select * from tb_borrowed;");
                                System.out.println("数据库中借阅记录如下：");
                                while (selectBorrowResult.next()) { //循环打印借书记录
                                    System.out.println("ID：" + selectBorrowResult.getInt(1)
                                            +"，借书人id：" + selectBorrowResult.getInt(2)
                                            +"，书的id：" + selectBorrowResult.getInt(3)
                                            + "，借书日期：" + selectBorrowResult.getString(4)
                                            + "，还书日期：" + selectBorrowResult.getString(5)
                                            +"，是否超出（0：未归还，1：已归还）：" + selectBorrowResult.getInt(6)
                                            + "，是否归还（0：未归还，1：已归还）：" + selectBorrowResult.getInt(7)
                                            + "，联系方式：" + selectBorrowResult.getString(8)  );
                                }
                                break;

                            case "4"://返回上一级
                                isGoBack = true ;
                                break;

                            case "5"://退出登录
                                isExitSystem = true;
                                break;//跳出little_switch3
                            default:
                                System.out.println("输入有误，请重新输入！");
                        }//end_little_switch3
                        if (isGoBack) {
                            break;//跳出当前little_while1，进入大的switch内
                        }
                        if (isExitSystem) {
                            break;//跳出little_while3
                        }//endif
                    }//end_little_while3---借阅管理
                    break;//跳出当前最大的_switch
                case "4": // 退出登录
                    isExitSystem = true;
                    break;//跳出当前大的switch
                default:
                    System.out.println("输入有误，请重新输入！");
            }//end_biggest_switch

            if (isExitSystem) {
                break;//跳出最大的while
            }//endif
        }//end_biggest_while
    }
}

