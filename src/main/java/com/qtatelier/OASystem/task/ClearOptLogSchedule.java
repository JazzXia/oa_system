package com.qtatelier.OASystem.task;

import com.qtatelier.OASystem.basics.optlog.mapper.OptLogMapper;
import com.qtatelier.config.ToolTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xiaweiwei
 * @version V1.0
 * @description
 * @package com.qtatelier.OASystem.task
 * @date 2020-01-20 10:28
 * @email xiaww@redoornetwork.com
 */
@Configuration
public class ClearOptLogSchedule {

	@Autowired
	private OptLogMapper optLogMapper;

	/**定时任务,按照创建时间,只清除超过24h之后的数据,每小时执行一次任务*/
	@Transactional(rollbackFor = Exception.class)
	@Scheduled( cron = "0 0 * * * ?")
	public void deleteOptLog()
	{
		//获取当前时间
		Calendar current = Calendar.getInstance();
		//改到前一天
		current.add( Calendar.DATE, -1 );
		String now = ToolTime.getStringByAllTimeSpacing( current.getTime() , "yyyy-MM-dd HH" );
		System.out.println(now);
		
		int isSuccess = optLogMapper.deleteInfoByLike( now );
		if(isSuccess < 1) {
			System.out.println("当前没有数据");
		}
	}


}
