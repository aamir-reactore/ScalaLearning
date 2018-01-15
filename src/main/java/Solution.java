import java.util.Scanner;
class employee
{
    int empID;
    String ename;

    void getinfo(int empid, String name)
    {
        empID = empid;
        ename = name;
    }

    void dispinfo()
    {
        System.out.println("Employee ID: " + empID + "\nEmployee Name: " + ename);
    }
}

class salary extends employee
{
    int msalary;
    void getsalary(int monthlysalary)
    {
        msalary = monthlysalary;
    }

    void dispsalary()
    {
        System.out.println("Sayary per month: " + msalary);
    }
}

interface others
{
    final int da1 = 200;
    final int hra1 = 300;
    final int ma1 = 100;
    void da_hra_ma();
}

class gross_salary extends salary
        implements others
{
    int grosstotal,da,hra,ma;

    public void da_hra_ma()
    {
        da = da1*(msalary/100);
        hra = hra1*(msalary/100);
        ma = ma1*(msalary/100);

        System.out.println("Interface variable: " + others.da1);

        System.out.println("Basic DA of " + empID + " is " + da);
        System.out.println("Basic HRA of " + empID + " is " + hra);
        System.out.println("Basic MA of " + empID + " is " + ma);
    }

    void disptotal()
    {
        grosstotal = msalary + da + hra + ma;
        System.out.println("Gross Salary of Employee " + empID+ " is " + grosstotal);
    }
}

public class Solution
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter Employee ID: ");
        int empid = in.nextInt();
        in.nextLine();

        System.out.println("Enter Employee Name: ");
        String name = in.nextLine();


        System.out.println("Enter Employee's monthly salary: ");
        int monthlysalary = in.nextInt();

        gross_salary obj = new gross_salary();

        obj.getinfo(empid,name);
        obj.getsalary(monthlysalary);
        obj.dispinfo();
        obj.dispsalary();
        obj.da_hra_ma();
        obj.disptotal();
    }
}