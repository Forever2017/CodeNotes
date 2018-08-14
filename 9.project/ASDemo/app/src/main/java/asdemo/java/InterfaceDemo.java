package asdemo.java;

/**
 * Created by 570014884 on 2018/7/6.
 * 接口（interface）的学习demo
 */
/** ===============运用的方法==================================*/
public class InterfaceDemo {
    public static void main(String args[]) {

        ComputerA ca = new ComputerA();
        ca.phoneInstall();

        ComputerB cb = new ComputerB();
        cb.plugin(new Phone());
    }
}

/**==========不使用接口=======================================*/
class ComputerA {
    /**
     * 安装手机驱动，并进行工作
     */
    public void phoneInstall()
    {
        //安装手机驱动
        System.out.println("未使用接口，安装手机驱动");
    }
    public void phoneWork()
    {
        //手机进行工作
    }
    /** 以此类推，后面增加 打印机、MP3 等等.. 每增加设备，ComputerA增加新的方法*/
}

/** ===========使用接口========================================*/
/**定义一个接口*/
interface USB {
    /*USB通用安装*/
    public void install();
    /*USB通用工作*/
    public void work();
}/**/

/**电脑使用*/
class ComputerB{
    /*任何设备，通用一个插件方法来进行安装和工作*/
    public void plugin(USB usb){
        //安装XX驱动，这取决于插入的设备
        usb.install();
        //XX进行工作，这取决于插入的设备
        usb.work();
    }
/**根据业务需求，增加需要在电脑上用到USB的设备*/
}

//手机使用USB
class Phone implements USB{  //这里的类就是实现类
    @Override
    public void install() {
        //手机进行驱动安装，具体手机怎么安装，在此处实现
        System.out.println("使用接口，手机类中单独实现驱动安装。");
    }
    @Override
    public void work() {
        //手机进行工作，具体手机怎么工作，在此处实现
    }
}

//根据业务需求，打印机也需要通过USB连接Computer
class Print implements USB{
    @Override
    public void install() {
        //打印机的安装
    }
    @Override
    public void work() {
        //打印机的工作
    }
}