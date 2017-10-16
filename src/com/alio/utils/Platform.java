package com.alio.utils;

import java.io.File;

public class Platform {
	public enum PlatformType {

		Any("any"),

		Linux("Linux"),

		Mac_OS("Mac OS"),

		Mac_OS_X("Mac OS X"),

		Windows("Windows"),

		OS2("OS/2"),

		Solaris("Solaris"),

		SunOS("SunOS"),

		MPEiX("MPE/iX"),

		HP_UX("HP-UX"),

		AIX("AIX"),

		OS390("OS/390"),

		FreeBSD("FreeBSD"),

		Irix("Irix"),

		Digital_Unix("Digital Unix"),

		NetWare_411("NetWare"),

		OSF1("OSF1"),

		OpenVMS("OpenVMS"),

		Others("Others");

		private PlatformType(String desc) {
			this.description = desc;
		}

		public String toString() {
			return description;
		}

		private String description;
	}

	private static final String OS = System.getProperty("os.name").toLowerCase();

	public static final String getArch() {
		return System.getProperty("os.arch");
	}

	public static final boolean isLinux() {
		return OS.indexOf("linux") >= 0;
	}

	public static final boolean isMacOS() {
		return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") < 0;
	}

	public static final boolean isMacOSX() {
		return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") > 0;
	}

	public static final boolean isWindows() {
		return OS.indexOf("windows") >= 0;
	}

	public static final boolean isOS2() {
		return OS.indexOf("os/2") >= 0;
	}

	public static final boolean isSolaris() {
		return OS.indexOf("solaris") >= 0;
	}

	public static final boolean isSunOS() {
		return OS.indexOf("sunos") >= 0;
	}

	public static final boolean isMPEiX() {
		return OS.indexOf("mpe/ix") >= 0;
	}

	public static final boolean isHPUX() {
		return OS.indexOf("hp-ux") >= 0;
	}

	public static final boolean isAix() {
		return OS.indexOf("aix") >= 0;
	}

	public static final boolean isOS390() {
		return OS.indexOf("os/390") >= 0;
	}

	public static final boolean isFreeBSD() {
		return OS.indexOf("freebsd") >= 0;
	}

	public static final boolean isIrix() {
		return OS.indexOf("irix") >= 0;
	}

	public static final boolean isDigitalUnix() {
		return OS.indexOf("digital") >= 0 && OS.indexOf("unix") > 0;
	}

	public static final boolean isNetWare() {
		return OS.indexOf("netware") >= 0;
	}

	public static final boolean isOSF1() {
		return OS.indexOf("osf1") >= 0;
	}

	public static final boolean isOpenVMS() {
		return OS.indexOf("openvms") >= 0;
	}

	/**
	 * 获取操作系统名字
	 * 
	 * @return 操作系统名
	 */
	public static final PlatformType getPlatformType() {
		PlatformType platform = null;
		if (isAix()) {
			platform = PlatformType.AIX;
		} else if (isDigitalUnix()) {
			platform = PlatformType.Digital_Unix;
		} else if (isFreeBSD()) {
			platform = PlatformType.FreeBSD;
		} else if (isHPUX()) {
			platform = PlatformType.HP_UX;
		} else if (isIrix()) {
			platform = PlatformType.Irix;
		} else if (isLinux()) {
			platform = PlatformType.Linux;
		} else if (isMacOS()) {
			platform = PlatformType.Mac_OS;
		} else if (isMacOSX()) {
			platform = PlatformType.Mac_OS_X;
		} else if (isMPEiX()) {
			platform = PlatformType.MPEiX;
		} else if (isNetWare()) {
			platform = PlatformType.NetWare_411;
		} else if (isOpenVMS()) {
			platform = PlatformType.OpenVMS;
		} else if (isOS2()) {
			platform = PlatformType.OS2;
		} else if (isOS390()) {
			platform = PlatformType.OS390;
		} else if (isOSF1()) {
			platform = PlatformType.OSF1;
		} else if (isSolaris()) {
			platform = PlatformType.Solaris;
		} else if (isSunOS()) {
			platform = PlatformType.SunOS;
		} else if (isWindows()) {
			platform = PlatformType.Windows;
		} else {
			platform = PlatformType.Others;
		}
		return platform;
	}

	public static final String getSharedLibPath(String path, String name) {
		if (isWindows()) {
			return String.format("%s%s%s.dll", path, File.separator, name);
		} else if (isLinux()) {
			return String.format("%s%s%s.so", path, File.separator, name);
		} else if (isMacOS()) {
			return String.format("%s%s%s.jnilib", path, File.separator, name);
		} else {
			return String.format("%s%s%s", path, File.separator, name);
		}
	}

	public static String getCommandFormat() {
		if (isWindows()) {
			return "cmd.exe /c %s";
		} else {
			return "%s";
		}
	}

	public static void main(String[] args) {
		System.out.println(Platform.getPlatformType());
		System.out.println(Platform.getArch());
		System.out.println("java_vendor:" + System.getProperty("java.vendor"));
		System.out.println("java_vendor_url:" + System.getProperty("java.vendor.url"));
		System.out.println("java_home:" + System.getProperty("java.home"));
		System.out.println("java_class_version:" + System.getProperty("java.class.version"));
		System.out.println("java_class_path:" + System.getProperty("java.class.path"));
		System.out.println("os_name:" + System.getProperty("os.name"));
		System.out.println("os_arch:" + System.getProperty("os.arch"));
		System.out.println("os_version:" + System.getProperty("os.version"));
		System.out.println("user_name:" + System.getProperty("user.name"));
		System.out.println("user_home:" + System.getProperty("user.home"));
		System.out.println("user_dir:" + System.getProperty("user.dir"));
		System.out.println("java_vm_specification_version:" + System.getProperty("java.vm.specification.version"));
		System.out.println("java_vm_specification_vendor:" + System.getProperty("java.vm.specification.vendor"));
		System.out.println("java_vm_specification_name:" + System.getProperty("java.vm.specification.name"));
		System.out.println("java_vm_version:" + System.getProperty("java.vm.version"));
		System.out.println("java_vm_vendor:" + System.getProperty("java.vm.vendor"));
		System.out.println("java_vm_name:" + System.getProperty("java.vm.name"));
		System.out.println("java_ext_dirs:" + System.getProperty("java.ext.dirs"));
		System.out.println("file_separator:" + System.getProperty("file.separator"));
		System.out.println("path_separator:" + System.getProperty("path.separator"));
		System.out.println("line_separator:" + System.getProperty("line.separator"));
	}

}
