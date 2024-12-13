import Link from "next/link";

import CaiaTitle from "@/core/shared/components/ui/caia-title";
import { ROUTES } from "@/core/shared/lib/constants/routes";
import SignUpForm from "../components/signup/signup-form";

const SignUpScreen = () => {
  return (
    <section className="flex h-full">
      <div className="w-0 xl:w-1/2 bg-lightprimary-200 hidden xl:block"></div>
      <div className="w-full xl:w-1/2 flex flex-col items-center justify-center">
        <header className="space-y-5 w-3/4 md:w-1/2">
          <CaiaTitle className="text-2xl" />
          <h2 className="font-semibold text-4xl">Crea una cuenta en C.A.I.A</h2>
        </header>
        <SignUpForm />
        <footer className="mt-10">
          <Link href={ROUTES.LOGIN}>Ya tienes una cuenta?</Link>
        </footer>
      </div>
    </section>
  );
};

export default SignUpScreen;
