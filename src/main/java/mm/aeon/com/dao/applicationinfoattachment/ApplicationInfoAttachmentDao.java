package mm.aeon.com.dao.applicationinfoattachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.common.BeanConverter;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.dto.applicationinfo.ApplicationInfoAttachmentDto;
import mm.aeon.com.zgen.entity.DaApplicationInfoAttachment;
import mm.aeon.com.zgen.mapper.DaApplicationInfoAttachmentMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ApplicationInfoAttachmentDao {

	@Autowired
	private DaApplicationInfoAttachmentMapper daApplicationInfoAttachmentMapper;

	@Autowired
	private BeanConverter beanConverter;

	public void insertApplicationInfoAttachment(ApplicationInfoAttachmentDto applicationInfoAttachmentDto) {
		DaApplicationInfoAttachment daApplicationInfoAttachment = beanConverter.convert(applicationInfoAttachmentDto, DaApplicationInfoAttachment.class);
		daApplicationInfoAttachment.setCreatedTime(CommonUtil.getCurrentTime());
		daApplicationInfoAttachmentMapper.insertSelective(daApplicationInfoAttachment);
	}

	public void updateApplicationInfoAttachment(ApplicationInfoAttachmentDto applicationInfoAttachmentDto) {
		DaApplicationInfoAttachment daApplicationInfoAttachment = beanConverter.convert(applicationInfoAttachmentDto, DaApplicationInfoAttachment.class);
		daApplicationInfoAttachment.setUpdatedTime(CommonUtil.getCurrentTime());
		daApplicationInfoAttachmentMapper.updateByPrimaryKeySelective(daApplicationInfoAttachment);
	}

}
