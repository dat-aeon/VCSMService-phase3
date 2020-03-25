package mm.aeon.com.common;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.google.myanmartools.TransliterateU2Z;
import com.google.myanmartools.TransliterateZ2U;
import com.google.myanmartools.ZawgyiDetector;

import mm.aeon.com.dto.applicationinfo.FreeApplicationInfoDto;

public class FreeApplicationInfoUnicodeConverter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6692881785399416060L;
	private static final ZawgyiDetector detector = new ZawgyiDetector();
	private static final TransliterateZ2U zawgyiToUnicodeConverter = new TransliterateZ2U("Zawgyi to Unicode");
	private static final TransliterateU2Z unicodeToZawgyiConverter = new TransliterateU2Z("Unicode to Zawgyi");

	public static void main(String[] args) {
		String Str = new String("ေမာင္ေအာင္မ်ိဳး");

		double fatherNameScore = detector.getZawgyiProbability(Str);
		if (fatherNameScore > 0.1) {
			Str = zawgyiToUnicodeConverter.convert(Str);
		}
		System.out.print("Return Value :");
		System.out.println(Str.toUpperCase());
	}

	public static FreeApplicationInfoDto convertZawgyiToUnicode(FreeApplicationInfoDto applicationInfoDto) {

		if (!StringUtils.isEmpty(applicationInfoDto.getName())) {
			applicationInfoDto.setName(applicationInfoDto.getName().toUpperCase());
			double nameScore = detector.getZawgyiProbability(applicationInfoDto.getName());
			if (nameScore > 0.1) {
				applicationInfoDto.setName(zawgyiToUnicodeConverter.convert(applicationInfoDto.getName()));
			}
		}

		if (!StringUtils.isEmpty(applicationInfoDto.getFatherName())) {
			applicationInfoDto.setFatherName(applicationInfoDto.getFatherName().toUpperCase());
			double fatherNameScore = detector.getZawgyiProbability(applicationInfoDto.getFatherName());
			if (fatherNameScore > 0.1) {
				applicationInfoDto.setFatherName(zawgyiToUnicodeConverter.convert(applicationInfoDto.getFatherName()));
			}
		}

		if (!StringUtils.isEmpty(applicationInfoDto.getNationalityOther())) {
			applicationInfoDto.setNationalityOther(applicationInfoDto.getNationalityOther().toUpperCase());
			double nationalityOtherScore = detector.getZawgyiProbability(applicationInfoDto.getNationalityOther());
			if (nationalityOtherScore > 0.1) {
				applicationInfoDto.setNationalityOther(zawgyiToUnicodeConverter.convert(applicationInfoDto.getNationalityOther()));
			}
		}

		if (!StringUtils.isEmpty(applicationInfoDto.getTypeOfResidenceOther())) {
			applicationInfoDto.setTypeOfResidenceOther(applicationInfoDto.getTypeOfResidenceOther().toUpperCase());
			double typeOfResidenceOtherScore = detector.getZawgyiProbability(applicationInfoDto.getTypeOfResidenceOther());
			if (typeOfResidenceOtherScore > 0.1) {
				applicationInfoDto.setTypeOfResidenceOther(zawgyiToUnicodeConverter.convert(applicationInfoDto.getTypeOfResidenceOther()));
			}
		}

		if (!StringUtils.isEmpty(applicationInfoDto.getLivingWithOther())) {
			applicationInfoDto.setLivingWithOther(applicationInfoDto.getLivingWithOther().toUpperCase());
			double livingWithOtherScore = detector.getZawgyiProbability(applicationInfoDto.getLivingWithOther());
			if (livingWithOtherScore > 0.1) {
				applicationInfoDto.setLivingWithOther(zawgyiToUnicodeConverter.convert(applicationInfoDto.getLivingWithOther()));
			}
		}

		if (!StringUtils.isEmpty(applicationInfoDto.getCurrentAddressFloor())) {
			applicationInfoDto.setCurrentAddressFloor(applicationInfoDto.getCurrentAddressFloor().toUpperCase());
			double currentAddressFloorScore = detector.getZawgyiProbability(applicationInfoDto.getCurrentAddressFloor());
			if (currentAddressFloorScore > 0.1) {
				applicationInfoDto.setCurrentAddressFloor(zawgyiToUnicodeConverter.convert(applicationInfoDto.getCurrentAddressFloor()));
			}
		}

		if (!StringUtils.isEmpty(applicationInfoDto.getCurrentAddressBuildingNo())) {
			applicationInfoDto.setCurrentAddressBuildingNo(applicationInfoDto.getCurrentAddressBuildingNo().toUpperCase());
			double currentAddressBuildingNoScore = detector.getZawgyiProbability(applicationInfoDto.getCurrentAddressBuildingNo());
			if (currentAddressBuildingNoScore > 0.1) {
				applicationInfoDto.setCurrentAddressBuildingNo(zawgyiToUnicodeConverter.convert(applicationInfoDto.getCurrentAddressBuildingNo()));
			}
		}

		if (!StringUtils.isEmpty(applicationInfoDto.getCurrentAddressRoomNo())) {
			applicationInfoDto.setCurrentAddressRoomNo(applicationInfoDto.getCurrentAddressRoomNo().toUpperCase());
			double currentAddressRoomNoScore = detector.getZawgyiProbability(applicationInfoDto.getCurrentAddressRoomNo());
			if (currentAddressRoomNoScore > 0.1) {
				applicationInfoDto.setCurrentAddressRoomNo(zawgyiToUnicodeConverter.convert(applicationInfoDto.getCurrentAddressRoomNo()));
			}
		}

		if (!StringUtils.isEmpty(applicationInfoDto.getCurrentAddressStreet())) {
			applicationInfoDto.setCurrentAddressStreet(applicationInfoDto.getCurrentAddressStreet().toUpperCase());
			double currentAddressStreetScore = detector.getZawgyiProbability(applicationInfoDto.getCurrentAddressStreet());
			if (currentAddressStreetScore > 0.1) {
				applicationInfoDto.setCurrentAddressStreet(zawgyiToUnicodeConverter.convert(applicationInfoDto.getCurrentAddressStreet()));
			}
		}

		if (!StringUtils.isEmpty(applicationInfoDto.getCurrentAddressQtr())) {
			applicationInfoDto.setCurrentAddressQtr(applicationInfoDto.getCurrentAddressQtr().toUpperCase());
			double currentAddressQtrScore = detector.getZawgyiProbability(applicationInfoDto.getCurrentAddressQtr());
			if (currentAddressQtrScore > 0.1) {
				applicationInfoDto.setCurrentAddressQtr(zawgyiToUnicodeConverter.convert(applicationInfoDto.getCurrentAddressQtr()));
			}
		}

		if (!StringUtils.isEmpty(applicationInfoDto.getPermanentAddressFloor())) {
			applicationInfoDto.setPermanentAddressFloor(applicationInfoDto.getPermanentAddressFloor().toUpperCase());
			double permanenttAddressFloorScore = detector.getZawgyiProbability(applicationInfoDto.getPermanentAddressFloor());
			if (permanenttAddressFloorScore > 0.1) {
				applicationInfoDto.setPermanentAddressFloor(zawgyiToUnicodeConverter.convert(applicationInfoDto.getPermanentAddressFloor()));
			}
		}

		if (!StringUtils.isEmpty(applicationInfoDto.getPermanentAddressBuildingNo())) {
			applicationInfoDto.setPermanentAddressBuildingNo(applicationInfoDto.getPermanentAddressBuildingNo().toUpperCase());
			double permanentAddressBuildingNoScore = detector.getZawgyiProbability(applicationInfoDto.getPermanentAddressBuildingNo());
			if (permanentAddressBuildingNoScore > 0.1) {
				applicationInfoDto.setPermanentAddressBuildingNo(zawgyiToUnicodeConverter.convert(applicationInfoDto.getPermanentAddressBuildingNo()));
			}
		}

		if (!StringUtils.isEmpty(applicationInfoDto.getPermanentAddressRoomNo())) {
			applicationInfoDto.setPermanentAddressRoomNo(applicationInfoDto.getPermanentAddressRoomNo().toUpperCase());
			double permanentAddressRoomNoScore = detector.getZawgyiProbability(applicationInfoDto.getPermanentAddressRoomNo());
			if (permanentAddressRoomNoScore > 0.1) {
				applicationInfoDto.setPermanentAddressRoomNo(zawgyiToUnicodeConverter.convert(applicationInfoDto.getPermanentAddressRoomNo()));
			}
		}

		if (!StringUtils.isEmpty(applicationInfoDto.getPermanentAddressStreet())) {
			applicationInfoDto.setPermanentAddressStreet(applicationInfoDto.getPermanentAddressStreet().toUpperCase());
			double permanentAddressStreetScore = detector.getZawgyiProbability(applicationInfoDto.getPermanentAddressStreet());
			if (permanentAddressStreetScore > 0.1) {
				applicationInfoDto.setPermanentAddressStreet(zawgyiToUnicodeConverter.convert(applicationInfoDto.getPermanentAddressStreet()));
			}
		}

		if (!StringUtils.isEmpty(applicationInfoDto.getPermanentAddressQtr())) {
			applicationInfoDto.setPermanentAddressQtr(applicationInfoDto.getPermanentAddressQtr().toUpperCase());
			double permanentAddressQtrScore = detector.getZawgyiProbability(applicationInfoDto.getPermanentAddressQtr());
			if (permanentAddressQtrScore > 0.1) {
				applicationInfoDto.setPermanentAddressQtr(zawgyiToUnicodeConverter.convert(applicationInfoDto.getPermanentAddressQtr()));
			}
		}

		if (applicationInfoDto.getApplicantCompanyInfoDto() != null) {

			if (!StringUtils.isEmpty(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyName())) {
				applicationInfoDto.getApplicantCompanyInfoDto().setCompanyName(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyName().toUpperCase());
				double companyNameScore = detector.getZawgyiProbability(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyName());
				if (companyNameScore > 0.1) {
					applicationInfoDto.getApplicantCompanyInfoDto()
							.setCompanyName(zawgyiToUnicodeConverter.convert(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyName()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getApplicantCompanyInfoDto().getDepartment())) {
				applicationInfoDto.getApplicantCompanyInfoDto().setDepartment(applicationInfoDto.getApplicantCompanyInfoDto().getDepartment().toUpperCase());
				double departmentScore = detector.getZawgyiProbability(applicationInfoDto.getApplicantCompanyInfoDto().getDepartment());
				if (departmentScore > 0.1) {
					applicationInfoDto.getApplicantCompanyInfoDto()
							.setDepartment(zawgyiToUnicodeConverter.convert(applicationInfoDto.getApplicantCompanyInfoDto().getDepartment()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getApplicantCompanyInfoDto().getPosition())) {
				applicationInfoDto.getApplicantCompanyInfoDto().setPosition(applicationInfoDto.getApplicantCompanyInfoDto().getPosition().toUpperCase());
				double positionScore = detector.getZawgyiProbability(applicationInfoDto.getApplicantCompanyInfoDto().getPosition());
				if (positionScore > 0.1) {
					applicationInfoDto.getApplicantCompanyInfoDto().setPosition(zawgyiToUnicodeConverter.convert(applicationInfoDto.getApplicantCompanyInfoDto().getPosition()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyStatusOther())) {
				applicationInfoDto.getApplicantCompanyInfoDto().setCompanyStatusOther(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyStatusOther().toUpperCase());
				double companyStatusOtherScore = detector.getZawgyiProbability(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyStatusOther());
				if (companyStatusOtherScore > 0.1) {
					applicationInfoDto.getApplicantCompanyInfoDto()
							.setCompanyStatusOther(zawgyiToUnicodeConverter.convert(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyStatusOther()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressBuildingNo())) {
				applicationInfoDto.getApplicantCompanyInfoDto()
						.setCompanyAddressBuildingNo(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressBuildingNo().toUpperCase());
				double companyAddressBuildingNoScore = detector.getZawgyiProbability(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressBuildingNo());
				if (companyAddressBuildingNoScore > 0.1) {
					applicationInfoDto.getApplicantCompanyInfoDto()
							.setCompanyAddressBuildingNo(zawgyiToUnicodeConverter.convert(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressBuildingNo()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressRoomNo())) {
				applicationInfoDto.getApplicantCompanyInfoDto().setCompanyAddressRoomNo(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressRoomNo().toUpperCase());
				double companyAddressRoomNoScore = detector.getZawgyiProbability(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressRoomNo());
				if (companyAddressRoomNoScore > 0.1) {
					applicationInfoDto.getApplicantCompanyInfoDto()
							.setCompanyAddressRoomNo(zawgyiToUnicodeConverter.convert(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressRoomNo()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressFloor())) {
				applicationInfoDto.getApplicantCompanyInfoDto().setCompanyAddressFloor(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressFloor().toUpperCase());
				double companyAddressFloorScore = detector.getZawgyiProbability(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressFloor());
				if (companyAddressFloorScore > 0.1) {
					applicationInfoDto.getApplicantCompanyInfoDto()
							.setCompanyAddressFloor(zawgyiToUnicodeConverter.convert(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressFloor()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressStreet())) {
				applicationInfoDto.getApplicantCompanyInfoDto().setCompanyAddressStreet(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressStreet().toUpperCase());
				double companyAddressStreetScore = detector.getZawgyiProbability(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressStreet());
				if (companyAddressStreetScore > 0.1) {
					applicationInfoDto.getApplicantCompanyInfoDto()
							.setCompanyAddressStreet(zawgyiToUnicodeConverter.convert(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressStreet()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressQtr())) {
				applicationInfoDto.getApplicantCompanyInfoDto().setCompanyAddressQtr(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressQtr().toUpperCase());
				double companyAddressQtrScore = detector.getZawgyiProbability(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressQtr());
				if (companyAddressQtrScore > 0.1) {
					applicationInfoDto.getApplicantCompanyInfoDto()
							.setCompanyAddressQtr(zawgyiToUnicodeConverter.convert(applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressQtr()));
				}
			}
		}

		if (applicationInfoDto.getEmergencyContactInfoDto() != null) {

			if (!StringUtils.isEmpty(applicationInfoDto.getEmergencyContactInfoDto().getName())) {
				applicationInfoDto.getEmergencyContactInfoDto().setName(applicationInfoDto.getEmergencyContactInfoDto().getName().toUpperCase());
				double nameScore = detector.getZawgyiProbability(applicationInfoDto.getEmergencyContactInfoDto().getName());
				if (nameScore > 0.1) {
					applicationInfoDto.getEmergencyContactInfoDto().setName(zawgyiToUnicodeConverter.convert(applicationInfoDto.getEmergencyContactInfoDto().getName()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getEmergencyContactInfoDto().getRelationshipOther())) {
				applicationInfoDto.getEmergencyContactInfoDto().setRelationshipOther(applicationInfoDto.getEmergencyContactInfoDto().getRelationshipOther().toUpperCase());
				double RelationshipOtherScore = detector.getZawgyiProbability(applicationInfoDto.getEmergencyContactInfoDto().getRelationshipOther());
				if (RelationshipOtherScore > 0.1) {
					applicationInfoDto.getEmergencyContactInfoDto()
							.setRelationshipOther(zawgyiToUnicodeConverter.convert(applicationInfoDto.getEmergencyContactInfoDto().getRelationshipOther()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressFloor())) {
				applicationInfoDto.getEmergencyContactInfoDto().setCurrentAddressFloor(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressFloor().toUpperCase());
				double currentAddressFloorScore = detector.getZawgyiProbability(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressFloor());
				if (currentAddressFloorScore > 0.1) {
					applicationInfoDto.getEmergencyContactInfoDto()
							.setCurrentAddressFloor(zawgyiToUnicodeConverter.convert(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressFloor()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressBuildingNo())) {
				applicationInfoDto.getEmergencyContactInfoDto()
						.setCurrentAddressBuildingNo(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressBuildingNo().toUpperCase());
				double currentAddressBuildingNoScore = detector.getZawgyiProbability(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressBuildingNo());
				if (currentAddressBuildingNoScore > 0.1) {
					applicationInfoDto.getEmergencyContactInfoDto()
							.setCurrentAddressBuildingNo(zawgyiToUnicodeConverter.convert(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressBuildingNo()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressRoomNo())) {
				applicationInfoDto.getEmergencyContactInfoDto().setCurrentAddressRoomNo(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressRoomNo().toUpperCase());
				double currentAddressRoomNoScore = detector.getZawgyiProbability(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressRoomNo());
				if (currentAddressRoomNoScore > 0.1) {
					applicationInfoDto.getEmergencyContactInfoDto()
							.setCurrentAddressRoomNo(zawgyiToUnicodeConverter.convert(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressRoomNo()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressStreet())) {
				applicationInfoDto.getEmergencyContactInfoDto().setCurrentAddressStreet(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressStreet().toUpperCase());
				double currentAddressStreetScore = detector.getZawgyiProbability(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressStreet());
				if (currentAddressStreetScore > 0.1) {
					applicationInfoDto.getEmergencyContactInfoDto()
							.setCurrentAddressStreet(zawgyiToUnicodeConverter.convert(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressStreet()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressQtr())) {
				applicationInfoDto.getEmergencyContactInfoDto().setCurrentAddressQtr(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressQtr().toUpperCase());
				double currentAddressQtrScore = detector.getZawgyiProbability(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressQtr());
				if (currentAddressQtrScore > 0.1) {
					applicationInfoDto.getEmergencyContactInfoDto()
							.setCurrentAddressQtr(zawgyiToUnicodeConverter.convert(applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressQtr()));
				}
			}
		}

		if (applicationInfoDto.getGuarantorInfoDto() != null) {

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getName())) {
				applicationInfoDto.getGuarantorInfoDto().setName(applicationInfoDto.getGuarantorInfoDto().getName().toUpperCase());
				double nameScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getName());
				if (nameScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto().setName(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getName()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getNationalityOther())) {
				applicationInfoDto.getGuarantorInfoDto().setNationalityOther(applicationInfoDto.getGuarantorInfoDto().getNationalityOther().toUpperCase());
				double nationalityOtherScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getNationalityOther());
				if (nationalityOtherScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto().setNationalityOther(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getNationalityOther()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getRelationshipOther())) {
				applicationInfoDto.getGuarantorInfoDto().setRelationshipOther(applicationInfoDto.getGuarantorInfoDto().getRelationshipOther().toUpperCase());
				double relationshipOtherScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getRelationshipOther());
				if (relationshipOtherScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto()
							.setRelationshipOther(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getRelationshipOther()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getTypeOfResidenceOther())) {
				applicationInfoDto.getGuarantorInfoDto().setTypeOfResidenceOther(applicationInfoDto.getGuarantorInfoDto().getTypeOfResidenceOther().toUpperCase());
				double typeOfResidenceOtherScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getTypeOfResidenceOther());
				if (typeOfResidenceOtherScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto()
							.setTypeOfResidenceOther(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getTypeOfResidenceOther()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getLivingWithOther())) {
				applicationInfoDto.getGuarantorInfoDto().setLivingWithOther(applicationInfoDto.getGuarantorInfoDto().getLivingWithOther().toUpperCase());
				double livingWithOtherScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getLivingWithOther());
				if (livingWithOtherScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto().setLivingWithOther(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getLivingWithOther()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getCompanyName())) {
				applicationInfoDto.getGuarantorInfoDto().setCompanyName(applicationInfoDto.getGuarantorInfoDto().getCompanyName().toUpperCase());
				double companyNameScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getCompanyName());
				if (companyNameScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto().setCompanyName(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getCompanyName()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getDepartment())) {
				applicationInfoDto.getGuarantorInfoDto().setDepartment(applicationInfoDto.getGuarantorInfoDto().getDepartment().toUpperCase());
				double departmentScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getDepartment());
				if (departmentScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto().setDepartment(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getDepartment()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getPosition())) {
				applicationInfoDto.getGuarantorInfoDto().setPosition(applicationInfoDto.getGuarantorInfoDto().getPosition().toUpperCase());
				double positionScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getPosition());
				if (positionScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto().setPosition(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getPosition()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressFloor())) {
				applicationInfoDto.getGuarantorInfoDto().setCurrentAddressFloor(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressFloor().toUpperCase());
				double currentAddressFloorScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressFloor());
				if (currentAddressFloorScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto()
							.setCurrentAddressFloor(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressFloor()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressBuildingNo())) {
				applicationInfoDto.getGuarantorInfoDto().setCurrentAddressBuildingNo(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressBuildingNo().toUpperCase());
				double currentAddressBuildingNoScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressBuildingNo());
				if (currentAddressBuildingNoScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto()
							.setCurrentAddressBuildingNo(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressBuildingNo()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressRoomNo())) {
				applicationInfoDto.getGuarantorInfoDto().setCurrentAddressRoomNo(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressRoomNo().toUpperCase());
				double currentAddressRoomNoScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressRoomNo());
				if (currentAddressRoomNoScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto()
							.setCurrentAddressRoomNo(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressRoomNo()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressStreet())) {
				applicationInfoDto.getGuarantorInfoDto().setCurrentAddressStreet(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressStreet().toUpperCase());
				double currentAddressStreetScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressStreet());
				if (currentAddressStreetScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto()
							.setCurrentAddressStreet(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressStreet()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressQtr())) {
				applicationInfoDto.getGuarantorInfoDto().setCurrentAddressQtr(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressQtr().toUpperCase());
				double currentAddressQtrScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressQtr());
				if (currentAddressQtrScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto()
							.setCurrentAddressQtr(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getCurrentAddressQtr()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressBuildingNo())) {
				applicationInfoDto.getGuarantorInfoDto().setCompanyAddressBuildingNo(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressBuildingNo().toUpperCase());
				double companyAddressBuildingNoScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressBuildingNo());
				if (companyAddressBuildingNoScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto()
							.setCompanyAddressBuildingNo(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressBuildingNo()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressRoomNo())) {
				applicationInfoDto.getGuarantorInfoDto().setCompanyAddressRoomNo(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressRoomNo().toUpperCase());
				double companyAddressRoomNoScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressRoomNo());
				if (companyAddressRoomNoScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto()
							.setCompanyAddressRoomNo(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressRoomNo()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressFloor())) {
				applicationInfoDto.getGuarantorInfoDto().setCompanyAddressFloor(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressFloor().toUpperCase());
				double companyAddressFloorScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressFloor());
				if (companyAddressFloorScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto()
							.setCompanyAddressFloor(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressFloor()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressStreet())) {
				applicationInfoDto.getGuarantorInfoDto().setCompanyAddressStreet(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressStreet().toUpperCase());
				double companyAddressStreetScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressStreet());
				if (companyAddressStreetScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto()
							.setCompanyAddressStreet(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressStreet()));
				}
			}

			if (!StringUtils.isEmpty(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressQtr())) {
				applicationInfoDto.getGuarantorInfoDto().setCompanyAddressQtr(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressQtr().toUpperCase());
				double companyAddressQtrScore = detector.getZawgyiProbability(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressQtr());
				if (companyAddressQtrScore > 0.1) {
					applicationInfoDto.getGuarantorInfoDto()
							.setCompanyAddressQtr(zawgyiToUnicodeConverter.convert(applicationInfoDto.getGuarantorInfoDto().getCompanyAddressQtr()));
				}
			}

		}

		return applicationInfoDto;
	}

}
