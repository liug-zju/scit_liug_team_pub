/*
 * 二进制序列   继承自抽象类Bits
 * 该类Bits_realize 是抽象类Bits的具体实现
 * 对public String toString()//将数组打印为可视01字符串，   //增加了参数   改成了   public String toString(byte[] m_bits) //将数组打印为可视01字符串，
 * 和public Bits copy() //复制     //增加了参数   改成了    public Bits copy(Bits B_bit)   
 *  但并未修改抽象类中的这两个类
 *  
 *  修改者   祝福
 * liug@ScitLiugTeam 2020.7.12
 */

package com.ScitLiugTeam.auto_scheduling;


/**
 *  继承自抽象类Bits，是抽象类Bits的具体实现，
 * @author 祝福
 */


public class Bits_realize extends Bits
{
	public static int[] binary_system(int n_system1,int n_digit1,int n_system2)//转换进制  将第一个int的数表示的进制转换 为第二个 int变量表示的进制数   ，返回int[]数组 
	{
		
		String str=""+n_digit1;
		int nI=Integer.parseInt(str,n_system1);//n_system1转换为十进制
		int nJ=Integer.parseInt(Integer.toString(nI, n_system2));//转换为n_system2进制
		System.out.println(""+n_system2+"进制数是："+nJ);
		int nDigit1Places = 0;//位数
		int nNum1=nJ;//计算用变量1 
		int nNum2=n_system2;//计算用变量2
		while(nNum1!=0)
		{
			nNum1=nNum1/10;
			nDigit1Places++;
		}
		System.out.println("位数是："+nDigit1Places);
		int[] nDigit1s = new int[nDigit1Places];
		int i = 0;//循环变量
		int nNum3=nJ;//计算用变量3 
		int nNum4=0;//计算用变量4
		while(nNum3!=0)
		{
			nDigit1s[i]=nNum3%10;
			nNum3=nNum3/10;
			i++;
		}
		return nDigit1s;
	}

	public byte[] newBits(int n) 
	{
		byte[] mNewbits = new byte[n];//一个字节 为 八位  ，新建长度为n的位数组   因为前两个字节是（也就是2个byte）一个整数，这个整数揭示m_bits数组的总长度（没有必要）
		//java中有.length的数组属性可以获取数组长度
		return mNewbits;
	}
	
	
	public byte[] fromString(String sBits) //根据01字符串新建二进制序列，字符串是包含了HEAD的////////////////
	{
		
		if(sBits.length()==0)
			return null;
		if(sBits.length()<8)
		{
			byte[] mBits = new byte[1];
			byte mN=0;
			int n=  Integer.parseInt(sBits.substring(0,sBits.length()));
			System.out.println("         "+Biannary2Decimal(n));
			//String result = Integer.toBinaryString(Biannary2Decimal(n));
			byte[] m_bits2=intToByte4(Biannary2Decimal(n));
			m_bits[0]=m_bits2[3];
			return m_bits;
		}
		
		if(sBits.length()>=8)
		{
			double k=sBits.length()/8;
			if(k%1!=0)
			{
				byte[] mBits = new byte[sBits.length()/8+1];
			}
			else
			{
				byte[] mBits = new byte[sBits.length()/8];
			}
		}
		
		System.out.println("\n\n"+m_bits.length);
		int nBeginIndex=0,nEndIndex=8;
		int nLen = sBits.length();
		for(int i=0;i<nLen/8;i++)
		{
			byte n2=0;
			
			/*
			int n= Integer.parseInt(sBits.substring(n_beginIndex,n_EndIndex));
			System.out.println(n);
			//n2+= n * Math.pow(10,j);
			n_beginIndex+=1;
			n_EndIndex+=1;
			*/
			int n= Integer.parseInt(sBits.substring(nBeginIndex,nEndIndex));
			n2=(byte) n;
			System.out.println(n);
			nBeginIndex+=8;
			nEndIndex+=8;
			if(nEndIndex>nLen)
			{
				return null;
			}
			m_bits[i]=n2;
		}
		
		
		
		
		
		return m_bits;
	}

	@Override
	public int length() //不需要
	{
		// TODO 自动生成的方法存根
		return 0;
	}

	public Bits copy(Bits B_bit) 
	{
		//m_bits
		for(int i=0;i<B_bit.m_bits.length;i++)
		{
			B_bit.m_bits[i]=m_bits[i];
		}
		
		return B_bit;
	}

	@Override//将第p1（包含）到第p2（包含）位之间的值设为nVal。调用者自行确保p2>p1>=0，且位数足够，不会溢出。
	public void assign(int nP1, int nP2, int nVal) //p1与p2表示的是二进制实体中的位置，不包括HEAD//////?
	{
		if(nP2>m_bits.length)
		{
			return ;
		}
		if(nP2>nP1&&nP1>=0)
		{
			byte[] mBit=intToByte4(nVal);
			int i=nP2-nP1;
			if(i<mBit.length)
			{
				return ;
			}
			for(int j=0;j<i;j++)
			{
				m_bits[nP1+j]=mBit[j];
			}
		}
		else 
		{
			return ;
		}
	}

	public int getValue(int nP1, int nP2) //获取第p1（包含）到第p2（包含）位之间的值/////////
	{
		//m_bits;
		/*
		int num = 0;
		byte[] m_bits2 = new byte[nP2-nP1+1];
		int j=0;
		for(int i=nP1;i<=nP2;i++)
		{
			m_bits2[j]=m_bits[nP1];
			j++;
		}
		
		*/
		if(nP2>m_bits.length)//判断是否符合条件
		{
			return -1;
		}
		
		byte[] mNums = new byte[nP2-nP1];
		for(int i=nP1;i<nP1+nP2;i++)
		{
			mNums[i-nP1]=m_bits[i];
		}
		int nNum = 1;
		for(int i=0;i<mNums.length;i++)
		{
			nNum*=mNums[i];
		}
		
		
		
		
		
		return nNum;
	}

	@Override
	public void reverse(int nP1, int nP2) //将第p1（包含）到第p2（包含）位之间的值倒序//
	{
		//m_bits
		if(nP1<=nP2&&nP2<=m_bits.length)//判断是否符合条件
		{
			return ;
		}
		byte[] mBits2 =new byte[nP2-nP1+1];
		int j=0;
		for(int i=nP1;i<=nP2;i++)
		{
			mBits2[j]=m_bits[nP1];
			j++;
		}
		for(int i=nP1;i<=nP2;i++)
		{
			m_bits[nP1]=mBits2[j];
			j--;
		}
	}

	@Override
	public int setBit(int nP, int nBit) //将第p位设为0或1。nBit只取0或1//////?
	{
		if(nBit==1||nBit==0)//判断是否符合条件
		{
			m_bits[nP]=(byte) nBit;
			return 0;
		}
		
		return -1;
	}

	@Override
	public void negate(int nP) //将第p位取反
	{
		if(nP>m_bits.length)
		{
			return ;
		}
		m_bits[nP]=(byte) (~m_bits[nP]);
	}

	@Override
	public byte[] cross(Bits bitsA, Bits bitsB, int nP1, int nP2)//两个Bits对象交换d第p1位到第p2位之间的数据
	{
		if(nP2<nP1)//判断是否符合条件
		{
			return null;
		}
		byte[] mBits2 =new byte[nP2-nP1+1];
		int nBitsa=bitsA.m_bits.length;
		int nBitsb=bitsA.m_bits.length;
		if(nP2>nBitsa||nP2>nBitsb)//判断是否符合条件
		{
			return null;
		}
		int j=0;
		for(int i=nP1;i<=nP2;i++)
		{
			mBits2[j]=bitsA.m_bits[nP1];
			bitsA.m_bits[nP1]=bitsB.m_bits[nP1];
			bitsB.m_bits[nP1]=mBits2[j];
			j++;
		}
		
		return mBits2;
	}

	@Override
	public void cross(Bits bitsA, Bits bitsB, int nP)//两个Bits对象第p位交叉。交叉算法是：与运算结果给A、或运算结果给B
	{
		int nBitsa=bitsA.m_bits.length;
		int nBitsb=bitsB.m_bits.length;
		byte nNum;
		if(nP>nBitsa||nP>nBitsb)//判断是否符合条件
		{
			return ;
		}
		nNum=(byte) ((bitsA.m_bits[nP])&(bitsB.m_bits[nP]));
		bitsB.m_bits[nP]=(byte) ((bitsA.m_bits[nP])|(bitsB.m_bits[nP]));
		bitsA.m_bits[nP]=nNum;
	}

	public String toString(byte[] m_bits) //将数组打印为可视01字符串，
	{
		if(m_bits.length==0)//判断是否符合条件
		{
			return "";
		}
		 //if (m_bits == null || m_bits.length != 4)
		// {
	    //        throw new IllegalArgumentException("byte数组必须不为空,并且是4位!");
	    //   }
		//Integer.toBinaryString(1088)
		String sStr="";//二进制数组
		for(int i=m_bits.length-1;i>=0;i--)
		{
			String sStr2=Integer.toBinaryString((int)m_bits[i]);
			if(sStr2.length()<8)
			{
				for(int j=0;j<=8-sStr2.length();j++)
				{
					sStr2="0"+sStr2;
				}
			}
			sStr=sStr+sStr2;
			
		}
		
		
		
		return sStr;
	}
	
	
	
	public static void main(String[] args) 
	{
		//int l=0b11;
		//System.out.println(p);
		
		
		Bits_realize n = new Bits_realize();
		n.m_bits=n.newBits(5);
		n.m_bits[0]=(byte) 011;
		
		System.out.println(n.m_bits[0]);
		
		System.out.println("字节数: "+n.m_bits.length);

		System.out.println("二进制位: "+n.m_bits.length*16);
		
		int[] k=binary_system(10,14,2);
		
		for(int i=k.length-1;i>=0;i--)
			System.out.println(k[i]);
		
		n.m_bits[0]=13;
		n.m_bits[1]=53;
		n.m_bits[2]=43;
		
		System.out.println("0到1这段里的数据"+n.getValue(0,2));
		
		
		byte[]  h=n.fromString("111111");
		System.out.println(h.length);
		for(int i=0;i<h.length;i++)
			System.out.println(h[i]);
		
		
		byte[] oo = new byte[2];
		oo[0]=43;
		oo[1]=53;
		String l=n.toString(oo);
		System.out.println(l);
		
		
		
		
		
		

	}

	
	
	public  static  Integer Biannary2Decimal(int bi)//将二进制的int转换为十进制int
	{
        String sBinStr = bi+"";
        Integer sum = 0;
        int nLen = sBinStr.length();
        for (int i=1;i<=nLen;i++){
            //第i位 的数字为：
            int dt = Integer.parseInt(sBinStr.substring(i-1,i));
            sum+=(int)Math.pow(2, nLen-i)*dt;
        }
        return  sum;
    }
	
	public static byte[] intToByte4(int sum) //将int型变量转换为byte数组
	{
        byte[] mArr = new byte[4];
        mArr[0] = (byte) (sum >> 24);
        mArr[1] = (byte) (sum >> 16);
        mArr[2] = (byte) (sum >> 8);
        mArr[3] = (byte) (sum & 0xff);
        return mArr;
    }

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Bits copy() {
		// TODO 自动生成的方法存根
		return null;
	}
	
	
	
}
