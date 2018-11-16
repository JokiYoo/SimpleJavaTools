import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class CopyUtils {
    
    
    /**
     * �÷�����������ͬ����ͬ����ֵ�ĺϲ������������ͬ������ͬһ���Զ���ֵ����ôsourceBean�е�ֵ�Ḳ��tagetBean�ص��ֵ
     * @param sourceBean    ����ȡ�Ķ���bean
     * @param targetBean    ���ںϲ��Ķ���bean
     * @return targetBean,�ϲ���Ķ���
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
    
    //���� combineBeans����
    public static void main(String[] args) {
    	Test1Bean sourceModel = new Test1Bean();    //    ��һ������
    	Test1Bean targetModel = new Test1Bean();    //    �ڶ���model����
        
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