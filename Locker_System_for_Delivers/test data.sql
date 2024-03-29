USE [master]
GO
/****** Object:  Database [locker]    Script Date: 2021/12/21 14:35:40 ******/
CREATE DATABASE [locker]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'locker', FILENAME = N'D:\Program Files\Microsoft SQL Server\MSSQL15.SERU\MSSQL\DATA\locker.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'locker_log', FILENAME = N'D:\Program Files\Microsoft SQL Server\MSSQL15.SERU\MSSQL\DATA\locker_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [locker] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [locker].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [locker] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [locker] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [locker] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [locker] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [locker] SET ARITHABORT OFF 
GO
ALTER DATABASE [locker] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [locker] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [locker] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [locker] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [locker] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [locker] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [locker] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [locker] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [locker] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [locker] SET  DISABLE_BROKER 
GO
ALTER DATABASE [locker] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [locker] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [locker] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [locker] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [locker] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [locker] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [locker] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [locker] SET RECOVERY FULL 
GO
ALTER DATABASE [locker] SET  MULTI_USER 
GO
ALTER DATABASE [locker] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [locker] SET DB_CHAINING OFF 
GO
ALTER DATABASE [locker] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [locker] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [locker] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [locker] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'locker', N'ON'
GO
ALTER DATABASE [locker] SET QUERY_STORE = OFF
GO
USE [locker]
GO
/****** Object:  Table [dbo].[Admin]    Script Date: 2021/12/21 14:35:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Admin](
	[AdminId] [int] IDENTITY(1,1) NOT NULL,
	[AdminName] [nvarchar](10) NULL,
	[AdminPwd] [nvarchar](20) NULL,
	[AdminLevel] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Box]    Script Date: 2021/12/21 14:35:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Box](
	[BId] [nvarchar](10) NOT NULL,
	[State] [int] NOT NULL,
	[OId] [int] NULL,
 CONSTRAINT [PK_Box] PRIMARY KEY CLUSTERED 
(
	[BId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 2021/12/21 14:35:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[CId] [int] IDENTITY(1,1) NOT NULL,
	[CName] [nchar](10) NOT NULL,
	[CMail] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[CId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Goods]    Script Date: 2021/12/21 14:35:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Goods](
	[GId] [int] IDENTITY(1,1) NOT NULL,
	[GName] [nchar](10) NOT NULL,
	[GPrice] [decimal](9, 2) NOT NULL,
 CONSTRAINT [PK_Goods] PRIMARY KEY CLUSTERED 
(
	[GId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 2021/12/21 14:35:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[OId] [int] IDENTITY(1,1) NOT NULL,
	[SId] [int] NOT NULL,
	[GId] [int] NOT NULL,
	[CId] [int] NOT NULL,
	[BId] [nvarchar](10) NOT NULL,
	[Date] [datetime] NOT NULL,
	[GNum] [int] NOT NULL,
	[GPrice_All] [decimal](9, 2) NOT NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[OId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Shop]    Script Date: 2021/12/21 14:35:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Shop](
	[SId] [int] IDENTITY(1,1) NOT NULL,
	[SName] [nvarchar](20) NOT NULL,
	[SMail] [nvarchar](30) NOT NULL,
 CONSTRAINT [PK_Shop] PRIMARY KEY CLUSTERED 
(
	[SId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Admin] ON 

INSERT [dbo].[Admin] ([AdminId], [AdminName], [AdminPwd], [AdminLevel]) VALUES (1, N'张三', N'123', 0)
INSERT [dbo].[Admin] ([AdminId], [AdminName], [AdminPwd], [AdminLevel]) VALUES (2, N'李四', N'123', 0)
INSERT [dbo].[Admin] ([AdminId], [AdminName], [AdminPwd], [AdminLevel]) VALUES (3, N'王五', N'123', 0)
INSERT [dbo].[Admin] ([AdminId], [AdminName], [AdminPwd], [AdminLevel]) VALUES (4, N'赵六', N'123', 1)
INSERT [dbo].[Admin] ([AdminId], [AdminName], [AdminPwd], [AdminLevel]) VALUES (5, N'陈七', N'123', 1)
SET IDENTITY_INSERT [dbo].[Admin] OFF
GO
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0501', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0502', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0503', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0504', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0505', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0506', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0507', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0508', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0509', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0510', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0511', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0512', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0513', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0514', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0515', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0516', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0517', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0518', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0519', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'0520', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2501', 1, 8)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2502', 1, 12)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2503', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2504', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2505', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2506', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2507', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2508', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2509', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2510', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2511', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2512', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2513', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2514', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2515', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2516', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2517', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2518', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2519', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2520', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2601', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2602', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2603', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2604', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2605', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2606', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2607', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2608', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2609', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2610', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2611', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2612', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2613', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2614', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2615', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2616', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2617', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2618', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2619', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2620', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2701', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2702', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2703', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2704', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2705', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2706', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2707', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2708', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2709', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2710', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2711', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2712', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2713', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2714', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2715', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2716', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2717', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2718', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2719', 0, NULL)
INSERT [dbo].[Box] ([BId], [State], [OId]) VALUES (N'2720', 0, NULL)
GO
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([CId], [CName], [CMail]) VALUES (1, N'胡图图       ', N'123@qq.com')
INSERT [dbo].[Customer] ([CId], [CName], [CMail]) VALUES (2, N'张美丽       ', N'123@qq.com')
INSERT [dbo].[Customer] ([CId], [CName], [CMail]) VALUES (3, N'张灯结彩      ', N'123@qq.com')
INSERT [dbo].[Customer] ([CId], [CName], [CMail]) VALUES (4, N'张牙舞爪      ', N'123@qq.com')
INSERT [dbo].[Customer] ([CId], [CName], [CMail]) VALUES (5, N'汪洋大海      ', N'123@qq.com')
INSERT [dbo].[Customer] ([CId], [CName], [CMail]) VALUES (6, N'陈陈相因      ', N'123@qq.com')
INSERT [dbo].[Customer] ([CId], [CName], [CMail]) VALUES (7, N'赵萤映雪      ', N'123@qq.com')
INSERT [dbo].[Customer] ([CId], [CName], [CMail]) VALUES (8, N'夏天来啦      ', N'123@qq.com')
INSERT [dbo].[Customer] ([CId], [CName], [CMail]) VALUES (9, N'下大雨啦      ', N'123@qq.com')
INSERT [dbo].[Customer] ([CId], [CName], [CMail]) VALUES (11, N'蜡笔小新      ', N'123@qq.com')
SET IDENTITY_INSERT [dbo].[Customer] OFF
GO
SET IDENTITY_INSERT [dbo].[Goods] ON 

INSERT [dbo].[Goods] ([GId], [GName], [GPrice]) VALUES (1, N'鸡排        ', CAST(13.50 AS Decimal(9, 2)))
INSERT [dbo].[Goods] ([GId], [GName], [GPrice]) VALUES (2, N'板栗        ', CAST(7.80 AS Decimal(9, 2)))
INSERT [dbo].[Goods] ([GId], [GName], [GPrice]) VALUES (3, N'草莓        ', CAST(15.60 AS Decimal(9, 2)))
INSERT [dbo].[Goods] ([GId], [GName], [GPrice]) VALUES (4, N'热干面       ', CAST(5.00 AS Decimal(9, 2)))
INSERT [dbo].[Goods] ([GId], [GName], [GPrice]) VALUES (5, N'烤肠        ', CAST(2.00 AS Decimal(9, 2)))
INSERT [dbo].[Goods] ([GId], [GName], [GPrice]) VALUES (6, N'关东煮       ', CAST(15.00 AS Decimal(9, 2)))
INSERT [dbo].[Goods] ([GId], [GName], [GPrice]) VALUES (7, N'披萨        ', CAST(36.60 AS Decimal(9, 2)))
INSERT [dbo].[Goods] ([GId], [GName], [GPrice]) VALUES (8, N'烤鸭        ', CAST(18.80 AS Decimal(9, 2)))
INSERT [dbo].[Goods] ([GId], [GName], [GPrice]) VALUES (9, N'猪耳朵       ', CAST(34.00 AS Decimal(9, 2)))
INSERT [dbo].[Goods] ([GId], [GName], [GPrice]) VALUES (10, N'辣子鸡       ', CAST(10.00 AS Decimal(9, 2)))
INSERT [dbo].[Goods] ([GId], [GName], [GPrice]) VALUES (11, N'五花肉       ', CAST(12.00 AS Decimal(9, 2)))
INSERT [dbo].[Goods] ([GId], [GName], [GPrice]) VALUES (12, N'拉面        ', CAST(13.00 AS Decimal(9, 2)))
SET IDENTITY_INSERT [dbo].[Goods] OFF
GO
SET IDENTITY_INSERT [dbo].[Order] ON 

INSERT [dbo].[Order] ([OId], [SId], [GId], [CId], [BId], [Date], [GNum], [GPrice_All]) VALUES (8, 2, 1, 1, N'2501', CAST(N'2021-12-21T00:00:00.000' AS DateTime), 3, CAST(17.89 AS Decimal(9, 2)))
INSERT [dbo].[Order] ([OId], [SId], [GId], [CId], [BId], [Date], [GNum], [GPrice_All]) VALUES (12, 3, 2, 2, N'2502', CAST(N'2021-12-21T00:00:00.000' AS DateTime), 2, CAST(34.30 AS Decimal(9, 2)))
SET IDENTITY_INSERT [dbo].[Order] OFF
GO
SET IDENTITY_INSERT [dbo].[Shop] ON 

INSERT [dbo].[Shop] ([SId], [SName], [SMail]) VALUES (2, N'美丽水果店', N'123@qq.com')
INSERT [dbo].[Shop] ([SId], [SName], [SMail]) VALUES (3, N'无语鸡排', N'123@qq.com')
INSERT [dbo].[Shop] ([SId], [SName], [SMail]) VALUES (5, N'今天便利店', N'123@qq.com')
INSERT [dbo].[Shop] ([SId], [SName], [SMail]) VALUES (6, N'来一碗麻辣烫', N'123@qq.com')
INSERT [dbo].[Shop] ([SId], [SName], [SMail]) VALUES (7, N'我不知道披萨', N'123@qq.com')
INSERT [dbo].[Shop] ([SId], [SName], [SMail]) VALUES (8, N'新年好煎饼', N'123@qq.com')
INSERT [dbo].[Shop] ([SId], [SName], [SMail]) VALUES (9, N'虾滑和鱼丸', N'123@qq.com')
INSERT [dbo].[Shop] ([SId], [SName], [SMail]) VALUES (10, N'鲁老师炸串', N'123@qq.com')
INSERT [dbo].[Shop] ([SId], [SName], [SMail]) VALUES (11, N'白色卫衣板栗', N'123@qq.com')
INSERT [dbo].[Shop] ([SId], [SName], [SMail]) VALUES (13, N'图书馆热干面', N'123@qq.com')
SET IDENTITY_INSERT [dbo].[Shop] OFF
GO
ALTER TABLE [dbo].[Box] ADD  CONSTRAINT [DF_Box_State]  DEFAULT ((0)) FOR [State]
GO
ALTER TABLE [dbo].[Goods] ADD  CONSTRAINT [DF_Goods_GPrice]  DEFAULT ((0.00)) FOR [GPrice]
GO
ALTER TABLE [dbo].[Order] ADD  CONSTRAINT [DF_Order_GPrice_All]  DEFAULT ((0.00)) FOR [GPrice_All]
GO
ALTER TABLE [dbo].[Box]  WITH CHECK ADD  CONSTRAINT [FK_Box_Order] FOREIGN KEY([OId])
REFERENCES [dbo].[Order] ([OId])
GO
ALTER TABLE [dbo].[Box] CHECK CONSTRAINT [FK_Box_Order]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Box] FOREIGN KEY([BId])
REFERENCES [dbo].[Box] ([BId])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Box]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Customer] FOREIGN KEY([CId])
REFERENCES [dbo].[Customer] ([CId])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Customer]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Goods] FOREIGN KEY([OId])
REFERENCES [dbo].[Goods] ([GId])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Goods]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Shop] FOREIGN KEY([SId])
REFERENCES [dbo].[Shop] ([SId])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Shop]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'管理员权限等级（0和1）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Admin', @level2type=N'COLUMN',@level2name=N'AdminLevel'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'状态（0为空1为已存放）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Box', @level2type=N'COLUMN',@level2name=N'State'
GO
USE [master]
GO
ALTER DATABASE [locker] SET  READ_WRITE 
GO
