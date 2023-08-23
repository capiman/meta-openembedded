DESCRIPTION = "A library for faking the system time in user-space programs"
SECTION = "libs"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCREV = "d475b925943ad404c6c728ac868dc73949e7281c"
SRC_URI = "\
    git://git@github.com/wolfcw/libfaketime.git;branch=master;protocol=https \
    "

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile () {
    oe_runmake
}
do_install () {
    install -d ${D}${libdir}/faketime
    oe_libinstall -C src libfaketime ${D}${libdir}/faketime
    install -d ${D}${bindir}
    install -m 0755 src/faketime ${D}${bindir}
}

PACKAGES =+ "lib${PN}"

FILES:${PN} = "${bindir}/faketime"
FILES:lib${PN} = "${libdir}/faketime/*"

INSANE_SKIP:${PN} += "dev-so"
INSANE_SKIP:lib${PN} += "dev-so"