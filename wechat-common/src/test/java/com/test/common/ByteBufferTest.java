package com.test.common;

import static org.junit.Assert.*;

import org.junit.Test;

import com.site.util.ByteBuffer;

public class ByteBufferTest {

	@Test
	public void byteBufferTest() {
		String s1 = "1211234112112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf12112341256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf256435123512430ldkvlkznoaeirjtgaioawejravd';slkjfoiweg'jdamsfocoieqrjoajvao;sdjfoiajfoaierjfpoajfvoiheorijgpajfvchoijrefpjreofjvcpaojecpaqjrewofgjrwegvgapjicohjeorgjregjvoajosdjfpoqewjrfowjregojocjdljcoajeroigf";
		String s2 = ";a;asjdfl;jweqoi[jfsad;fjcowejgfojsdojaif;asjdfl;jweqoi[jfsad;fjcowejgfojsdojaif;asjdfl;jweqoi[jfsad;fjcowejgfojsdojaif;asjdfl;jweqoi[jfsad;fjcowejgfojsdojaif;asjdfl;jweqoi[jfsad;fjcowejgfojsdojaif;asjdfl;jweqoi[jfsad;fjcowejgfojsdojaif;asjdfl;jweqoi[jfsad;fjcowejgfojsdojaif;asjdfl;jweqoi[jfsad;fjcowejgfojsdojaifsjdfl;jweqoi[jfsad;fjcowejgfojsdojaif";
		byte[] b1 = s1.getBytes();
		byte[] b2 = s2.getBytes();
		ByteBuffer bb = new ByteBuffer();
		bb.push(b2);
		bb.push(b1);
		byte[] buffer = bb.get();
		String s2_ = new String(buffer, 0, b2.length);
		String s1_ = new String(buffer, b2.length, b1.length);
		assertEquals(s1, s1_);
		assertEquals(s2, s2_);
	}
}
