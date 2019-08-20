package cn.ffcs.tsp.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 
 * 功能描述：车载终端应答表
 *
 * @author ：guok
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
@Document(collection="t_msg_info")
public class MsgInfo {
	
	//id
	@Id
	private String _id;
	//标识
	@Field("tid")
	private String tid;
	//车辆vin
	@Field("vin")
	private String vin;
	//消息类型1：远程控制 ，2：消息推送 ，3：蓝牙绑车 ，4：蓝牙解绑
	@Field("type")
	private String type;
	//流水号
	@Field("lsh")
	private Integer lsh;
	//下发的消息
	@Field("sendmsg")
	private String sendmsg;
	//消息插入时间
	@Field("data_time")
	private String dataTime;
	//下发的时间
	@Field("send_time")
	private String sendTime;
	//下发应答
	@Field("sendack")
	private byte[] sendack;
	//下发应答的时间
	@Field("sendack_time")
	private String sendackTime;
	//下发结果应答
	@Field("sendres")
	private byte[] sendres;
	//下发结果应答
	@Field("sendres_time")
	private String sendresTime;
	//营销ID
	@Field("marketingId")
	private String marketingId;
	//下发应答的结果
	@Field("sendack_state")
	private String sendackState;
	//下发结果应答的状态
	@Field("sendres_state")
	private String sendresState;
	@Field("search_filte")
	private String searchFilte ;
	@Field("start_date")
	private String startDate;
	@Field("end_date")
	private String endDate;
	
	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年7月2日 上午10:56:58
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public String getSearchFilte() {
		return searchFilte;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年7月2日 上午10:57:03
	 *
	 * @param search_filte
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setSearchFilte(String searchFilte) {
		this.searchFilte = searchFilte;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月14日 下午3:52:59
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public String getSendackState() {
		return sendackState;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月14日 下午3:53:05
	 *
	 * @param sendackState
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setSendackState(String sendackState) {
		this.sendackState = sendackState;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月14日 下午3:53:09
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public String getSendresState() {
		return sendresState;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月14日 下午3:53:13
	 *
	 * @param sendresState
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setSendresState(String sendresState) {
		this.sendresState = sendresState;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月14日 下午3:53:33
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public byte[] getSendack() {
		return sendack;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月14日 下午3:53:37
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public String getSendresTime() {
		return sendresTime;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月11日 上午9:43:40
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月11日 上午9:43:44
	 *
	 * @param vin
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月12日 下午3:29:18
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public String getSendackTime() {
		return sendackTime;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月12日 下午3:29:23
	 *
	 * @param sendackTime
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setSendackTime(String sendackTime) {
		this.sendackTime = sendackTime;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月12日 下午3:29:30
	 *
	 * @param sendack
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setSendack(byte[] sendack) {
		this.sendack = sendack;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月12日 下午3:29:34
	 *
	 * @param sendresTime
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setSendresTime(String sendresTime) {
		this.sendresTime = sendresTime;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月11日 上午9:43:53
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public byte[] getSendres() {
		return sendres;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月11日 上午9:43:55
	 *
	 * @param sendres
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setSendres(byte[] sendres) {
		this.sendres = sendres;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月10日 下午4:50:58
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public String getMarketingId() {
		return marketingId;
	}

	
	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月10日 下午4:51:02
	 *
	 * @param marketingId
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setMarketingId(String marketingId) {
		this.marketingId = marketingId;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月10日 下午4:39:04
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public String get_id() {
		return _id;
	}
	
	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月10日 下午4:39:07
	 *
	 * @param _id
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void set_id(String _id) {
		this._id = _id;
	}
	
	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月10日 下午4:39:10
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public String getTid() {
		return tid;
	}
	
	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月10日 下午4:39:13
	 *
	 * @param tid
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}
	
	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月10日 下午4:39:17
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月10日 下午4:39:20
	 *
	 * @param type
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月20日 上午9:54:56
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public Integer getLsh() {
		return lsh;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月20日 上午9:55:05
	 *
	 * @param lsh
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setLsh(Integer lsh) {
		this.lsh = lsh;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月10日 下午4:39:32
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public String getSendmsg() {
		return sendmsg;
	}
	
	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月10日 下午4:39:36
	 *
	 * @param sendmsg
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setSendmsg(String sendmsg) {
		this.sendmsg = sendmsg;
	}
	
	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月12日 下午3:00:45
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public String getDataTime() {
		return dataTime;
	}
	
	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月12日 下午3:00:58
	 *
	 * @param dataTime
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月12日 下午3:01:02
	 *
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public String getSendTime() {
		return sendTime;
	}
	
	/**
	 * 
	 * 功能描述：
	 *
	 * @author 何佳杰
	 * 创建日期 ：2018年6月12日 下午3:01:09
	 *
	 * @param sendTime
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


}
