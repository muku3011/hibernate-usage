**Command to initialize token:**

##### PS C:\SoftHSM2\bin> ./softhsm2-util.exe --init-token --slot 0 --label "FirstToken"

	=== SO PIN (4-255 characters) ===
	Please enter SO PIN: ****
	Please reenter SO PIN: ****
	=== User PIN (4-255 characters) ===
	Please enter user PIN: ****
	Please reenter user PIN: ****
	The token has been initialized and is reassigned to slot 1445067893

**Command to show all slot information:**

##### PS C:\SoftHSM2\bin> .\softhsm2-util.exe --show-slots

	Available slots:
	Slot 1445067893
		Slot info:
			Description:      SoftHSM slot ID 0x5621fc75
			Manufacturer ID:  SoftHSM project
			Hardware version: 2.5
			Firmware version: 2.5
			Token present:    yes
		Token info:
			Manufacturer ID:  SoftHSM project
			Model:            SoftHSM v2
			Hardware version: 2.5
			Firmware version: 2.5
			Serial number:    ee71e8c15621fc75
			Initialized:      yes
			User PIN init.:   yes
			Label:            FirstToken
	Slot 1
		Slot info:
			Description:      SoftHSM slot ID 0x1
			Manufacturer ID:  SoftHSM project
			Hardware version: 2.5
			Firmware version: 2.5
			Token present:    yes
		Token info:
			Manufacturer ID:  SoftHSM project
			Model:            SoftHSM v2
			Hardware version: 2.5
			Firmware version: 2.5
			Serial number:
			Initialized:      no
			User PIN init.:   no
			Label: