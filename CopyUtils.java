import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class CopyUtils {
    
    
    /**
     * 该方法是用于相同对象不同属性值的合并，如果两个相同对象中同一属性都有值，那么sourceBean中的值会覆盖tagetBean重点的值
     * @param sourceBean    被提取的对象bean
     * @param targetBean    用于合并的对象bean
     * @return targetBean,合并后的对象
     */
    private  Test1Bean combineSydwCore(Test1Bean sourceBean,Test1Bean targetBean){
        Class sourceBeanClass = sourceBean.getClass();
        Class targetBeanClass = targetBean.getClass();
        
        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
        Field[] targetFields = targetBeanClass.getDeclaredFields();
        for(int i=0; i<sourceFields.length; i++){
            Field sourceField = sourceFields[i]; 
            if(Modifier.isStatic(sourceField.getModifiers())){
            	continue;
            }
            Field targetField = targetFields[i];  
            if(Modifier.isStatic(targetField.getModifiers())){
                    continue;
            }
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            try {
                if( !(sourceField.get(sourceBean) == null) &&  !"serialVersionUID".equals(sourceField.getName().toString())){
                	int old =  Integer.valueOf(sourceField.get(sourceBean).toString());
                	int news = Integer.valueOf(targetField.get(targetBean).toString());
                	
                	
                    targetField.set(targetBean,  old+news);
                }
                
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return targetBean;
    }
    
    //测试 combineBeans方法
    public static void main(String[] args) {
    	Test1Bean sourceModel = new Test1Bean();    //    第一个对象
    	Test1Bean targetModel = new Test1Bean();    //    第二个model对象
        
        sourceModel.setProp1(1);
        sourceModel.setProp2(1);
        
        targetModel.setProp1(1);
        targetModel.setProp2(2);
        targetModel.setProp3(2);
        
        CopyUtils test = new CopyUtils();
        test.combineSydwCore(sourceModel, targetModel);
        
        System.out.println(targetModel.toString());
    }
}