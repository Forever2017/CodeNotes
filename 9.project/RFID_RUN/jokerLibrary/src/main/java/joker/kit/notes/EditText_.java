package joker.kit.notes;

public class EditText_ {/**

 不自动获取焦点!
     在EditText的父级控件中增加属性
     android:focusable="true"
     android:focusableInTouchMode="true"

 限制输入类型
     代码：et_lxnr.setInputType(InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
     xml：android:inputType="number"

 限制输入长度（如限制输入最大长度10）
     代码：et_lxnr.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
     xml：android:maxLength="10"

 限制输入固定的某些字符（如123456xyz）
     代码：et_lxnr.setKeyListener(DigitsKeyListener.getInstance(“123456xyz”);
     xml：android:digits="@string/input_num_character"


 */
}
