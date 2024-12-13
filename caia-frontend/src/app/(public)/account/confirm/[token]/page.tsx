import ConfirmScreen from "@/core/users/presentation/screens/confirm.screen";

interface Props {
  params: Promise<{
    token: string;
  }>;
}

const ConfirmAccoutPage = async (props: Props) => {
  const params = await props.params;
  return <ConfirmScreen token={params.token} />;
};

export default ConfirmAccoutPage;
