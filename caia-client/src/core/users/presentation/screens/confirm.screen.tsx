import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/core/shared/components/ui/card";
import { sleep } from "@/core/shared/lib/utils";
import { confirmAccount } from "../../data/actions/confirm-account.action";
import Link from "next/link";
import { ROUTES } from "@/core/shared/lib/constants/routes";

interface Props {
  token: string;
}
const ConfirmScreen = async ({ token }: Props) => {
  await sleep(2000);

  const response = await confirmAccount(token);

  console.log({ response });

  if (response.status === 200) {
    return (
      <div className="h-screen flex items-center justify-center">
        <Card className="bg-lightprimary-300 w-[50%] text-center">
          <CardHeader>
            <CardTitle className="text-2xl">Cuenta verificada</CardTitle>
            <CardDescription></CardDescription>
          </CardHeader>
          <CardContent>
            <Link href={ROUTES.LOGIN}>Ya puedes iniciar sesion</Link>
          </CardContent>
        </Card>
      </div>
    );
  }

  return (
    <div className="h-screen flex items-center justify-center">
      <Card className="bg-lightprimary-100 w-[50%] text-center">
        <CardHeader>
          <CardTitle className="text-2xl">Hubo un error</CardTitle>
          <CardDescription></CardDescription>
        </CardHeader>
        <CardContent>
          <p>No pudimos verificar tu cuenta</p>
        </CardContent>
      </Card>
    </div>
  );
};

export default ConfirmScreen;
