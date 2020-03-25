package mm.aeon.com.dao.purchaseinfoattachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.common.BeanConverter;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoAttachmentDto;
import mm.aeon.com.zgen.entity.DaPurchaseInfoAttachment;
import mm.aeon.com.zgen.mapper.DaPurchaseInfoAttachmentMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class PurchaseInfoAttachmentDao {

	@Autowired
	private DaPurchaseInfoAttachmentMapper daPurchaseInfoAttachmentMapper;

	@Autowired
	private BeanConverter beanConverter;

	public void insertPurchaseInfoAttachment(PurchaseInfoAttachmentDto purchaseInfoAttachmentDto) {
		DaPurchaseInfoAttachment daPurchaseInfoAttachment = beanConverter.convert(purchaseInfoAttachmentDto, DaPurchaseInfoAttachment.class);
		daPurchaseInfoAttachment.setCreatedTime(CommonUtil.getCurrentTime());
		daPurchaseInfoAttachmentMapper.insertSelective(daPurchaseInfoAttachment);
	}

	public void updatePurchaseInfoAttachment(PurchaseInfoAttachmentDto purchaseInfoAttachmentDto) {
		DaPurchaseInfoAttachment daPurchaseInfoAttachment = beanConverter.convert(purchaseInfoAttachmentDto, DaPurchaseInfoAttachment.class);
		daPurchaseInfoAttachment.setUpdatedTime(CommonUtil.getCurrentTime());
		daPurchaseInfoAttachmentMapper.updateByPrimaryKeySelective(daPurchaseInfoAttachment);
	}

}
