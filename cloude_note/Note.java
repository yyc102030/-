import java.util.Date; 
public class Note {
	private String cn_note_id;	//笔记ID 
	private String cn_notebook_id;	//笔记本ID 
	private String cn_user_id;	//用户ID 
	private String cn_note_status_id;	//笔记状态ID:备用 
	private String cn_note_type_id;	//笔记本类型ID：备用 
	private String cn_note_title;	//笔记标题 
	private String cn_note_body;	//笔记内容 
	private long cn_note_create_time;	//笔记创建时间 
	private long cn_note_last_modify_time;	//笔记最近修改时间 

	public Note (){

	}

	public void setCn_note_id(String cn_note_id){
		this.cn_note_id=cn_note_id;
	}
	public String getCn_note_id(){
		return cn_note_id;
	}

	public void setCn_notebook_id(String cn_notebook_id){
		this.cn_notebook_id=cn_notebook_id;
	}
	public String getCn_notebook_id(){
		return cn_notebook_id;
	}

	public void setCn_user_id(String cn_user_id){
		this.cn_user_id=cn_user_id;
	}
	public String getCn_user_id(){
		return cn_user_id;
	}

	public void setCn_note_status_id(String cn_note_status_id){
		this.cn_note_status_id=cn_note_status_id;
	}
	public String getCn_note_status_id(){
		return cn_note_status_id;
	}

	public void setCn_note_type_id(String cn_note_type_id){
		this.cn_note_type_id=cn_note_type_id;
	}
	public String getCn_note_type_id(){
		return cn_note_type_id;
	}

	public void setCn_note_title(String cn_note_title){
		this.cn_note_title=cn_note_title;
	}
	public String getCn_note_title(){
		return cn_note_title;
	}

	public void setCn_note_body(String cn_note_body){
		this.cn_note_body=cn_note_body;
	}
	public String getCn_note_body(){
		return cn_note_body;
	}

	public void setCn_note_create_time(long cn_note_create_time){
		this.cn_note_create_time=cn_note_create_time;
	}
	public long getCn_note_create_time(){
		return cn_note_create_time;
	}

	public void setCn_note_last_modify_time(long cn_note_last_modify_time){
		this.cn_note_last_modify_time=cn_note_last_modify_time;
	}
	public long getCn_note_last_modify_time(){
		return cn_note_last_modify_time;
	}

	@Override
	public String toString(){
		return " Note [cn_note_id=" + cn_note_id+ " , cn_notebook_id=" + cn_notebook_id+ " , cn_user_id=" + cn_user_id+ " , cn_note_status_id=" + cn_note_status_id+ " , cn_note_type_id=" + cn_note_type_id+ " , cn_note_title=" + cn_note_title+ " , cn_note_body=" + cn_note_body+ " , cn_note_create_time=" + cn_note_create_time+ " , cn_note_last_modify_time=" + cn_note_last_modify_time+"]";
	}

}

