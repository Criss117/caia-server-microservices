import NextAuth from "next-auth";
import Credentials from "next-auth/providers/credentials";
import { loginAction } from "./core/users/data/actions/login.action";

export const { handlers, signIn, signOut, auth } = NextAuth({
  providers: [
    Credentials({
      credentials: {
        email: {},
        password: {},
      },
      authorize: async (credentials) => {
        const response = await loginAction({
          email: credentials?.email as string,
          password: credentials?.password as string,
        });

        console.log({ response });

        console.log(response.data);

        return response.data;
      },
    }),
  ],
  callbacks: {
    async jwt({ token, user }) {
      return { ...token, ...user };
    },
    async session({ session, token }) {
      return { ...session, ...token };
    },
  },
  pages: {
    signIn: "/account/login",
  },
});

declare module "next-auth" {
  interface Session {
    email: string;
    jwt: string;
    status: boolean;
  }
}
